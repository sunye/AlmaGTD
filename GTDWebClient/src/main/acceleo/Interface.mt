<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import ImportUtil
%>

<%script type="uml.Interface" name="interface" file="<%package.name.packageToPath() + "/" +name%>.java"%>
package <%package.name%>;

<%imports%>
//<%startUserCode%> for imports
import java.util.List;
//<%endUserCode%>

<%visibility%> interface <%name%> {
<%for (ownedOperation){%>
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].inParameter.sep(", ")%>);
<%}%>
}

<%script type="Parameter" name="inParameter"%>
<%resolveType%> <%name%>

<%script type="uml.Interface" name="imports"%>
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