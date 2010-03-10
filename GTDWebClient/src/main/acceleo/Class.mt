<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import ImportUtil
%>

<%script type="uml.Class" name="classe" file="<%package.name.packageToPath() + "/" +name%>.java"%>
package <%package.name%>;

<%imports%>
//<%startUserCode%> for imports
import java.util.List;
//<%endUserCode%>

public class <%name%> <%if (generalization){%>extends <%superClass.name%><%}%> <%if (interfaceRealization){%>implements <%for (interfaceRealization.sep(", ")){%><%contract.name%><%}%><%}%> {
	
	<%for (attribute[association == null]){%>
	private <%declaration%>
	<%}%>
	
	<%for (attribute[association != null]){%>
	private <%declaration%>
	<%}%>
	
	public <%name%> () {
		super();
	}
	 	
	<%for (interfaceRealization.contract.ownedOperation){%>
	<%methodsInterfaces%>
	<%}%>
	 	
	<%for (ownedOperation){%>
	<%methods%>
	<%}%>
}

<%script type="Parameter" name="inParameter"%>
<%resolveType%> <%name%>

<%script type="Property" name="declaration"%>
	<%if (upper == -1){%>
List<<%type.name%>> <%name%>;
	<%}else{%>
<%type.name%> <%name%>;
	<%}%>

<%script type="Operation" name="methods"%>
	/**
	<%for (ownedParameter[direction == "in"]){%>
	* @param <%name%>
	<%}%>
	<%for (ownedParameter[direction == "return"]){%>
	* @return <%resolveType%>
	<%}%>
	*/
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].inParameter.sep(", ")%>) {
		//<%startUserCode%> for <%name%> method body
		//TODO
		<%if (ownedParameter[direction == "return"]){%>
		return null;
		<%}%>
		//<%endUserCode%>
	}
	
<%script type="Operation" name="methodsInterfaces"%>
	/**
	<%for (ownedParameter[direction == "in"]){%>
	* @param <%name%>
	<%}%>
	<%for (ownedParameter[direction == "return"]){%>
	* @return <%resolveType%>
	<%}%>
	*/
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].inParameter.sep(", ")%>) {
		//<%startUserCode%> for <%name%> method body
		//TODO
		<%if (ownedParameter[direction == "return"]){%>
		return null;
		<%}%>
		//<%endUserCode%>
	}
	 	
<%script type="Class" name="imports"%>
	<%if (superClass != null && superClass.package.name != package.name){%>
		<%(superClass.package.name+"."+superClass.name).addImport()%>
	<%}%>

	<%for (interfaceRealization){%>
		<%(contract.package.name+"."+contract.name).addImport()%>
	<%}%>

	<%for (attribute){%>
		<%if (type.package.name != "UMLPrimitiveTypes" &&!type.name.startsWith("List")){%>
			<%(type.package.name+"."+type.name).addImport()%>
		<%}%>
	<%}%>
	
	<%for (ownedOperation){%>
		<%for (ownedParameter){%>
			<%if (type.package.name != "UMLPrimitiveTypes" && !type.name.startsWith("List")){%>
				<%(type.package.name+"."+type.name).addImport()%>
			<%}%>
		<%}%>
	<%}%>
	
<%getImports()%>

<%script type="Parameter" name="resolveType"%>
	<%if (upper == -1){%>
List<<%type.name%>>
	<%}else{%>
<%type.name%>
	<%}%>
