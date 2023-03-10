package net.villenium.game.api.athena.util;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.lang.reflect.Type;
import java.util.*;

public class ItemSerializer implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int amount = 0;
        Material material = Material.getMaterial(jsonObject.get("material").getAsString());
        String name = "";
        Map<Enchantment, Integer> enchants = null;
        List<String> lore = new ArrayList<>();
        int repairPenalty = 0;
        if (jsonObject.has("amount")) {
            amount = jsonObject.get("amount").getAsInt();
        }
        if (jsonObject.has("name")) {
            name = jsonObject.get("name").getAsString();
        }
        if (jsonObject.has("enchantments")) {
            enchants = getEnchantments(jsonObject.get("enchantments").getAsString());
        }
        if (jsonObject.has("lore")) {
            JsonArray array = jsonObject.getAsJsonArray("lore");
            for (int j = 0; j < array.size(); j++) {
                lore.add(array.get(j).getAsString());
            }
        }
        if (jsonObject.has("repairPenalty")) {
            repairPenalty = jsonObject.get("repairPenalty").getAsInt();
        }
        ItemStack stuff = new ItemStack(material, 1, (short) jsonObject.get("durability").getAsInt());
        if ((material == Material.BOOK_AND_QUILL || material == Material.WRITTEN_BOOK) && jsonObject.has("book-meta")) {
            BookMeta meta = getBookMeta(jsonObject.getAsJsonObject("book-meta"));
            stuff.setItemMeta(meta);
        } else if (material == Material.ENCHANTED_BOOK && jsonObject.has("book-meta")) {
            EnchantmentStorageMeta meta = getEnchantedBookMeta(jsonObject.getAsJsonObject("book-meta"));
            stuff.setItemMeta(meta);
        } else if (isLeatherArmor(material) && jsonObject.has("armor-meta")) {
            LeatherArmorMeta meta;
            meta = getLeatherArmorMeta(jsonObject.getAsJsonObject("armor-meta"));
            stuff.setItemMeta(meta);
        } else if (material == Material.SKULL_ITEM && jsonObject.has("skull-meta")) {
            SkullMeta meta = getSkullMeta(jsonObject.getAsJsonObject("skull-meta"));
            stuff.setItemMeta(meta);
        } else if (material == Material.FIREWORK && jsonObject.has("firework-meta")) {
            FireworkMeta meta = getFireworkMeta(jsonObject.getAsJsonObject("firework-meta"));
            stuff.setItemMeta(meta);
        }
        ItemMeta meta = stuff.getItemMeta();
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(lore);
        }
        stuff.setItemMeta(meta);
        if (repairPenalty != 0) {
            Repairable rep = (Repairable) meta;
            rep.setRepairCost(repairPenalty);
            stuff.setItemMeta((ItemMeta) rep);
        }

        if (enchants != null) {
            stuff.addUnsafeEnchantments(enchants);
        }
        stuff.setAmount(amount);
        return stuff;
    }

    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext context) {
        JsonObject values = new JsonObject();
        if (itemStack == null) {
            return null;
        }
        int amount = 0;
        boolean hasMeta = itemStack.hasItemMeta();
        String name = null, enchants = null;
        String[] lore = null;
        int repairPenalty = 0;
        Material material = itemStack.getType();
        JsonObject bookMeta = null, armorMeta = null, skullMeta = null, fwMeta = null;
        if (material == Material.BOOK_AND_QUILL || material == Material.WRITTEN_BOOK) {
            bookMeta = serializeBookMeta((BookMeta) itemStack.getItemMeta());
        } else if (material == Material.ENCHANTED_BOOK) {
            bookMeta = serializeEnchantedBookMeta((EnchantmentStorageMeta) itemStack.getItemMeta());
        } else if (isLeatherArmor(material)) {
            armorMeta = serializeArmor((LeatherArmorMeta) itemStack.getItemMeta());
        } else if (material == Material.SKULL_ITEM) {
            skullMeta = serializeSkull((SkullMeta) itemStack.getItemMeta());
        } else if (material == Material.FIREWORK) {
            fwMeta = serializeFireworkMeta((FireworkMeta) itemStack.getItemMeta());
        }
        if (itemStack.getAmount() > 0) {
            values.add("amount", new JsonPrimitive(itemStack.getAmount()));
        }
        if (hasMeta) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.hasDisplayName()) {
                name = meta.getDisplayName();
            }
            if (meta.hasLore()) {
                lore = meta.getLore().toArray(new String[]{});
            }
            if (meta.hasEnchants())
                enchants = serializeEnchantments(meta.getEnchants());
            if (meta instanceof Repairable) {
                Repairable rep = (Repairable) meta;
                if (rep.hasRepairCost()) {
                    repairPenalty = rep.getRepairCost();
                }
            }
        }
        values.add("material", new JsonPrimitive(itemStack.getType().name()));
        values.add("durability", new JsonPrimitive(itemStack.getDurability()));
        if (name != null) {
            values.add("name", new JsonPrimitive(name));
        }
        if (enchants != null) {
            values.add("enchantments", new JsonPrimitive(enchants));
        }
        if (lore != null) {
            JsonArray jsonArray = new JsonArray();
            for (String loreElement : lore) {
                jsonArray.add(new JsonPrimitive(loreElement));
            }
            values.add("lore", jsonArray);
        }
        if (repairPenalty != 0) {
            values.add("repairPenalty", new JsonPrimitive(repairPenalty));
        }
        if (bookMeta != null && bookMeta.size() > 0) {
            values.add("book-meta", bookMeta);
        }
        if (armorMeta != null && armorMeta.size() > 0) {
            values.add("armor-meta", armorMeta);
        }
        if (skullMeta != null && skullMeta.size() > 0) {
            values.add("skull-meta", skullMeta);
        }
        if (fwMeta != null && fwMeta.size() > 0) {
            values.add("firework-meta", fwMeta);
        }
        return values;
    }

    private BookMeta getBookMeta(JsonObject json) {
        ItemStack dummyItems = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) dummyItems.getItemMeta();
        String title = null, author = null;
        JsonArray pages = null;
        if (json.has("title")) {
            title = json.get("title").getAsString();
        }
        if (json.has("author")) {
            author = json.get("author").getAsString();
        }
        if (json.has("pages")) {
            pages = json.get("pages").getAsJsonArray();
        }
        if (title != null) {
            meta.setTitle(title);
        }
        if (author != null) {
            meta.setAuthor(author);
        }
        if (pages != null) {
            List<String> allPages = new ArrayList<>();
            for (int i = 0; i < pages.size(); i++) {
                String page = pages.get(i).getAsString();
                if (page.isEmpty() || page == null) {
                    page = "";
                }
                allPages.add(i, page);
            }
            meta.setPages(allPages);
        }
        return meta;
    }

    private JsonObject serializeBookMeta(BookMeta meta) {
        JsonObject root = new JsonObject();
        if (meta.hasTitle()) {
            root.add("title", new JsonPrimitive(meta.getTitle()));
        }
        if (meta.hasAuthor()) {
            root.add("author", new JsonPrimitive(meta.getAuthor()));
        }
        if (meta.hasPages()) {
            JsonArray jsonArray = new JsonArray();
            for (String page : meta.getPages()) {
                jsonArray.add(new JsonPrimitive(page));
            }
            root.add("pages", jsonArray);
        }
        return root;
    }

    private EnchantmentStorageMeta getEnchantedBookMeta(JsonObject json) {
        ItemStack dummyItems = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) dummyItems.getItemMeta();
        if (json.has("enchantments")) {
            Map<Enchantment, Integer> enchants = getEnchantments(json.get("enchantments").getAsString());
            for (Enchantment e : enchants.keySet()) {
                meta.addStoredEnchant(e, enchants.get(e), true);
            }
        }
        return meta;
    }

    private JsonObject serializeEnchantedBookMeta(EnchantmentStorageMeta meta) {
        JsonObject root = new JsonObject();
        String enchants = serializeEnchantments(meta.getStoredEnchants());
        root.add("enchantments", new JsonPrimitive(enchants));
        return root;
    }

    private JsonObject serializeArmor(LeatherArmorMeta meta) {
        JsonObject root = new JsonObject();
        root.add("color", serializeColor(meta.getColor()));
        return root;
    }

    private LeatherArmorMeta getLeatherArmorMeta(JsonObject json) {
        ItemStack dummyItems = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) dummyItems.getItemMeta();
        if (json.has("color")) {
            meta.setColor(getColor(json.get("color").getAsJsonObject()));
        }
        return meta;
    }

    private String serializeEnchantments(Map<Enchantment, Integer> enchantments) {
        String serialized = "";
        for (Enchantment e : enchantments.keySet()) {
            serialized += e.getName() + ":" + enchantments.get(e) + ";";
        }
        return serialized;
    }

    private Map<Enchantment, Integer> getEnchantments(String serializedEnchants) {
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
        if (serializedEnchants.isEmpty()) {
            return enchantments;
        }
        String[] enchants = serializedEnchants.split(";");
        for (int i = 0; i < enchants.length; i++) {
            String[] ench = enchants[i].split(":");
            enchantments.put(Enchantment.getByName(ench[0]), Integer.parseInt(ench[1]));
        }
        return enchantments;
    }

    private FireworkMeta getFireworkMeta(JsonObject json) {
        FireworkMeta dummy = (FireworkMeta) new ItemStack(Material.FIREWORK).getItemMeta();
        dummy.setPower(json.get("power").getAsInt());
        JsonArray effects = json.getAsJsonArray("effects");
        for (int i = 0; i < effects.size(); i++) {
            JsonObject effectDto = effects.get(i).getAsJsonObject();
            FireworkEffect effect = getFireworkEffect(effectDto);
            if (effect != null) {
                dummy.addEffect(effect);
            }
        }
        return dummy;
    }

    private JsonObject serializeFireworkMeta(FireworkMeta meta) {
        JsonObject root = new JsonObject();
        root.add("power", new JsonPrimitive(meta.getPower()));
        JsonArray effects = new JsonArray();
        for (FireworkEffect e : meta.getEffects()) {
            effects.add(serializeFireworkEffect(e));
        }
        root.add("effects", effects);
        return root;
    }

    private FireworkEffect getFireworkEffect(JsonObject json) {
        FireworkEffect.Builder builder = FireworkEffect.builder();
        JsonArray colors = json.getAsJsonArray("colors");
        for (int j = 0; j < colors.size(); j++) {
            builder.withColor(getColor(colors.get(j).getAsJsonObject()));
        }
        JsonArray fadeColors = json.getAsJsonArray("fade-colors");
        for (int j = 0; j < fadeColors.size(); j++) {
            builder.withFade(getColor(colors.get(j).getAsJsonObject()));
        }
        if (json.get("flicker").getAsBoolean()) {
            builder.withFlicker();
        }
        if (json.get("trail").getAsBoolean()) {
            builder.withTrail();
        }
        builder.with(FireworkEffect.Type.valueOf(json.get("type").getAsString()));
        return builder.build();
    }

    private JsonObject serializeFireworkEffect(FireworkEffect effect) {
        JsonObject root = new JsonObject();
        JsonArray colors = new JsonArray();
        for (Color c : effect.getColors()) {
            colors.add(serializeColor(c));
        }
        root.add("colors", colors);
        JsonArray fadeColors = new JsonArray();
        for (Color c : effect.getFadeColors()) {
            fadeColors.add(serializeColor(c));
        }
        root.add("fade-colors", fadeColors);
        root.add("flicker", new JsonPrimitive(effect.hasFlicker()));
        root.add("trail", new JsonPrimitive(effect.hasTrail()));
        root.add("type", new JsonPrimitive(effect.getType().name()));
        return root;
    }


    private Color getColor(JsonObject color) {
        int r = 0, g = 0, b = 0;
        if (color.has("red")) {
            r = color.get("red").getAsInt();
        }
        if (color.has("green")) {
            g = color.get("green").getAsInt();
        }
        if (color.has("blue")) {
            b = color.get("blue").getAsInt();
        }
        return Color.fromRGB(r, g, b);
    }

    private JsonObject serializeColor(Color color) {
        JsonObject root = new JsonObject();
        root.add("red", new JsonPrimitive(color.getRed()));
        root.add("green", new JsonPrimitive(color.getGreen()));
        root.add("blue", new JsonPrimitive(color.getBlue()));
        return root;
    }

    private boolean isLeatherArmor(Material material) {
        return material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE ||
                material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS;
    }

    private JsonObject serializeSkull(SkullMeta meta) {
        JsonObject root = new JsonObject();
        if (meta.hasOwner()) {
            root.add("owner", new JsonPrimitive(meta.getOwningPlayer().getUniqueId().toString()));
        }
        return root;
    }

    private SkullMeta getSkullMeta(JsonObject meta) {
        ItemStack dummyItems = new ItemStack(Material.SKULL_ITEM);
        SkullMeta dummyMeta = (SkullMeta) dummyItems.getItemMeta();
        if (meta.has("owner")) {
            dummyMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(meta.get("owner").getAsString())));
        }
        return dummyMeta;
    }
}

