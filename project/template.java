/*
 *  Example class containing methods to read and display CPU, PCI and USB information
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

public class template 
{
    public static void showPCI() {
        pciInfo pci = new pciInfo();
        pci.read();

        System.out.println("\nThis machine has "+
            pci.busCount()+" PCI bus ");

        // Iterate through each bus
        for (int i = 0; i < pci.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                pci.deviceCount(i)+" devices");
		System.out.println("");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information
            for (int j = 0; j < 32; j++) {
                if (pci.functionCount (i, j) > 0) {
		if (pci.functionCount (i, j) < 2) {

                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" function");
}
		else {
                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" functions");
}

                    // Iterate through up to 8 functions per device.
                    for (int k = 0; k < 8; k++) {
                        if (pci.functionPresent (i, j, k) > 0) {
			if (pci.vendorID(i,j,k) == 32902) {
			System.out.print("Intel Corporation: ");
		if (pci.productID(i,j,k) == 4663) {
		System.out.println("440FX - 82441FX PMC [Natoma]"); 
	}
		if (pci.productID(i,j,k) == 28672) {
                System.out.println("82371SB PIIX3 ISA [Natoma/Triton II]"); 
        }
		if (pci.productID(i,j,k) == 28945) {
                System.out.println("82371AB/EB/MB PIIX4 IDE"); 
        }
		if (pci.productID(i,j,k) == 4110) {
                System.out.println("82540EM Gigabit Ethernet Controller"); 
        }
		if (pci.productID(i,j,k) == 9237) {
                System.out.println("82801AA AC'97 Audio Controller"); 
        }
		if (pci.productID(i,j,k) == 28947) {
                System.out.println("82371AB/EB/MB PIIX4 ACPI"); 
        }
		if (pci.productID(i,j,k) == 9820) {
                System.out.println("82801FB/FBM/FR/FW/FRW (ICH6 Family) USB2 EHCI Controller"); 
        }
		if (pci.productID(i,j,k) == 10281) {
                System.out.println("82801HM/HEM (ICH8M/ICH8M-E) SATA Controller [ACHI mode]"); 
        }

} 
                         else if (pci.vendorID(i,j,k) == 5549) {
                        System.out.println("VMWare: SVGA II Adapter");
} 
			else if (pci.vendorID(i,j,k) == 33006 ) {
                        System.out.println("InnoTek Systemberatung GmbH: VirtualBox Guest Service");
}
			else if (pci.vendorID(i,j,k) == 4203) {
                        System.out.println("Apple Inc.: KeyLargo/Intrepid USB");
} 
			else if (pci.vendorID(i,j,k) == 6900) {
                        System.out.println("Red Hat, Inc.: Virtio file system");
} 
 
 
                                else {

				   System.out.println("Bus "+i+" device "+j+" function "+k+
                                " has vendor "+String.format("0x%04X", pci.vendorID(i,j,k))+
                                " and product "+String.format("0x%04X", pci.productID(i,j,k)));
                        }
					System.out.println("");
                    }
                }
            }
        }
    }
}
    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis machine has "+
            usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                    " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                    " and product "+String.format("0x%04X", usb.productID(i,j)));
            }
        }
    }

    public static void showCPU()
    {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Show CPU model, CPU sockets and cores per socket
        System.out.println("CPU " + cpu.getModel() + " has "+
            cpu.socketCount() + " sockets each with "+
            cpu.coresPerSocket() + " cores");

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d="+cpu.l1dCacheSize()/1024+"KB");
           System.out.println("l1i="+cpu.l1iCacheSize()/1024+"KB");
           System.out.println("l2="+cpu.l2CacheSize()/1024+"KB");
           System.out.println("l3="+cpu.l3CacheSize()/1024+"KB");

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);
        System.out.println("core 1 Idle="+cpu.getIdleTime(1)+"%");
	cpu.read(1);
        System.out.println("core 1 System="+cpu.getSystemTime(1)+"%");
	cpu.read(1);
        System.out.println("core 1 User="+cpu.getUserTime(1)+"%");

    }

    public static void showDisk()
    {
        diskInfo disk = new diskInfo();
        disk.read();

        // Iterate through all of the disks
        for (int i = 0; i < disk.diskCount(); i++) {
            System.out.println ("disk "+disk.getName(i)+" has "+
                disk.getTotal(i)+" blocks, of which "+
                disk.getUsed(i)+" are used");
        }
    }

    public static void showMem()
    {
        memInfo mem = new memInfo();
        mem.read();

        System.out.println ("There is "+mem.getTotal()+" memory of which "+
            mem.getUsed()+" is used");
    }

    public static void main(String[] args)
    {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        showCPU();
        showPCI();
        showUSB();
        showDisk();
        showMem();
    }
}

