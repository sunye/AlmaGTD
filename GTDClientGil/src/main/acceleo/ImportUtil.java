import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


public class ImportUtil {
	private EList<String> packages = new BasicEList<String>();
	public void addToImport(String pck){
		if(!packages.contains(pck)){
			packages.add(pck);
		}
	}
	public String getImports(EObject o){
		String temp = "";
		for(String s : packages)
			temp += "import " + s + ";\n";
		return temp;
	}
}
