public class mainfile {
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

    //Peter
    public static void showCPU()
    {}

    //Aaron
    public static void showPCI()
    {
	pciInfo pci = new pciInfo();
        pci.read();
	}

    //Rosie
    public static void showUSB()
    {}

    //Waleed
    public static void showDisk()
    {}

    //Waleed
    public static void showMem()
    {}
}
