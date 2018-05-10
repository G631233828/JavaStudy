import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class test {

	
	public static void main(String[] args) throws SigarException {
	
			try {
				property();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private static void property() throws SigarException, InterruptedException {
		Sigar s = new Sigar();
		while(true){
		System.out.println(s.getMem());
		Thread.sleep(1000);
		}
	}
}
