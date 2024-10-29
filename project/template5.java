public class template5 { 
    // Vendor mapping class using arrays
    static class VendorMapper {
        private static final int[] vendorIds = {0x08de, 0x18f8, 0x1c87, 0x1630, 0x08c8};
        private static final String[] vendorNames = {"Unknown Vendor", "[Maxxter]", "2N TELEKOMUNIKACE a.s.", "2Wire, Inc.", "???"};

        public static String getVendorName(int vendorId) {
            for (int i = 0; i < vendorIds.length; i++) {
                // Compare the decimal vendorId directly to the vendorIds
                if (vendorIds[i] == vendorId) {
                    return vendorNames[i];
                }
            }
            return "Unknown Vendor";
        }
    }

    public static void showUSB() {
        UsbInfo usb = new UsbInfo(); // Assuming UsbInfo is defined elsewhere
        usb.read();
        System.out.println("\nThis machine has " + usb.busCount() + " USB buses ");

        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices");

            for (int j = 1; j <= usb.deviceCount(i); j++) {
                int vendorId = usb.hexVendorID(i, j); // Get vendor ID (in decimal)
                String vendorName = VendorMapper.getVendorName(vendorId); // Get vendor name
                int productId = usb.productID(i, j); // Get product ID

                // Output the vendor ID and name in hexadecimal format
                System.out.printf("Bus %d device %d has vendor ID 0x%04X (%s) and product ID 0x%04X%n",
                                  i, j, vendorId, vendorName, productId);
            }
        }
    }

  /*  public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        showUSB();
      */
    }
}
