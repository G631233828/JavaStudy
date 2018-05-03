import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class test {

	
	public static void main(String[] args) throws SigarException {
	
			property();
		
	}

	private static void property() throws SigarException {
		Sigar s = new Sigar();
		System.out.println(s.getMem());
		
	}
}
