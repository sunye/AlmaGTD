<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import PathUtil
import ImportUtil
%>

<%script type="uml.Interface" name="interface" file="<%package.name.toPath() + "/" +name%>.java"%>
package <%package.name%>;
<%gestImport%>
//<%startUserCode%> for imports
import java.util.List;

//<%endUserCode%>

<%visibility%> interface <%name%> {
<%for (ownedOperation){%>
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].declaration.sep(", ")%>);
	<%}%>
}


<%script type="Parameter" name="declaration"%>
<%resolveType%> <%name%>

<%script type="uml.Interface" name="gestImport"%>
<%-- import des types des signatures de méthodes --%>
<%for (ownedOperation){%>
<%for (ownedParameter){%>
<%if (type.package.name != "UMLPrimitiveTypes"){%>
<%if (!type.name.startsWith("List")){%>
<%(type.package.name+"."+type.name).addToImport()%>
<%}%>
<%}%><%}%><%}%>
<%getImports()%>

<%script type="Parameter" name="resolveType"%>
<%if (upper == -1){%>List<<%type.name%>><%}else{%><%type.name%><%}%>