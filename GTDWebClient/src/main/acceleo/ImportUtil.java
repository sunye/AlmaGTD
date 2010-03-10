import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class ImportUtil {

	private EList<String> imports = new BasicEList<String>();
	
	public void addImport(String pck) {
		if(!imports.contains(pck)) {
			imports.add(pck);
		}
	}
	
	public String getImports(EObject o) {
		String tmp = "";
		for(String s : imports) {
			tmp += "import " + s + ";\n";
		}
		return tmp;
	}
	
	public String packageToPath(String path) {
		return path.replaceAll("\\.", "/");
	}
}
