public class template { // Ensure your class is named appropriately
    // Vendor mapping class using arrays
    static class VendorMapper {
        private static final int[] vendorIds = {0x1234, 0x5678}; // Add more IDs as needed
        private static final String[] vendorNames = {"Example Vendor 1", "Example Vendor 2"};

        public static String getVendorName(int vendorId) {
            for (int i = 0; i < vendorIds.length; i++) {
                if (vendorIds[i] == vendorId) {
                    return vendorNames[i];
                }
            }
            return "Unknown Vendor";
        }
    }

    public static void showUSB() {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis machine has " + usb.busCount() + " USB buses ");

        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices");

            for (int j = 1; j <= usb.deviceCount(i); j++) {
                int vendorId = usb.vendorID(i, j);
                String vendorName = VendorMapper.getVendorName(vendorId);
                int productId = usb.productID(i, j);

                System.out.println("Bus " + i + " device " + j +
                        " has vendor ID " + String.format("0x%04X", vendorId) +
                        " (" + vendorName + ")" +
                        " and product ID " + String.format("0x%04X", productId));
            }
        }
    }

   

    public static void main(String[] args) {
        System.loadLibrary("sysinfo");

        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        
        showUSB();
       
    }
}
