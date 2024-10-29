public class template {
    // Vendor mapping class using arrays
    static class VendorMapper {
        private static final int[] vendorIds = {0x08DE, 0x18F8, 0x1C87, 0x1630};
        private static final String[] vendorNames = {"Rosie", "[Maxxter]", "2N TELEKOMUNIKACE a.s.", "2Wire, Inc."};

        public static String getVendorName(int vendorId) {
            for (int i = 0; i < vendorIds.length; i++) {
                if (vendorIds[i] == vendorId) {
                    return vendorNames[i];
                }
            }
            return "Unknown Vendor"; // Default return if vendor ID is not found
        }
    }

    public static void showUSB() {
        UsbInfo usb = new UsbInfo(); // Assuming UsbInfo is defined correctly
        usb.read(); // Read USB information
        System.out.println("\nThis machine has " + usb.busCount() + " USB buses.");

        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices.");

            for (int j = 1; j <= usb.deviceCount(i); j++) {
                int vendorId = usb.vendorID(i, j); // Get vendor ID
                String vendorName = VendorMapper.getVendorName(vendorId); // Get vendor name
                int productId = usb.productID(i, j); // Get product ID

                // Print the vendor ID and name in a formatted manner
                System.out.printf("Bus %d device %d has vendor ID 0x%04X (%s) and product ID 0x%04X%n",
                                  i, j, vendorId, vendorName, productId);
            }
        }
    }

    public static void main(String[] args) {
        System.loadLibrary("sysinfo"); // Load the library for USB information

        
        showCPU();
        cpu.read(0); // Read CPU information

        showUSB(); // Display USB information
    }
}
