package com.example.anil.laundry.cloth_price_list.man;

public class ModelClass_ClothPriceList_Man {

        private String cloth_image;
        private String cloth_name;
        private String cloth_price;

        public ModelClass_ClothPriceList_Man() {}

        public ModelClass_ClothPriceList_Man(String cloth_image, String cloth_name, String cloth_price) {
            this.cloth_image = cloth_image;
            this.cloth_name = cloth_name;
            this.cloth_price = cloth_price;
        }

        public String getCloth_image() {
            return cloth_image;
        }

        public String getCloth_name() {
            return cloth_name;
        }

        public String getCloth_price() {
            return cloth_price;
        }

}
