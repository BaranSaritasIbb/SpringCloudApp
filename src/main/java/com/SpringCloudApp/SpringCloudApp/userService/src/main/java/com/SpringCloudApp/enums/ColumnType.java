package com.SpringCloudApp.enums;

public enum ColumnType {
    METIN("metin"),
    TAM_SAYI("tam sayı"),
    MANTIKSAL("mantıksal"),
    ONDALIK("ondalık"),
    FIYAT("fiyat"),
    TARIH("tarih"),
    ZAMAN_DAMGASI("zaman damgası"),
    UZUN("uzun tam sayı"),
    KÜSURATLI("küsuratlı");

    private final String type;

    ColumnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Dizeyi ColumnType'a dönüştüren metod
    public static ColumnType fromString(String text) {
        for (ColumnType b : ColumnType.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Desteklenmeyen sütun tipi: " + text);
    }
}
