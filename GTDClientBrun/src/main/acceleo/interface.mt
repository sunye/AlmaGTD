<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
%>

<%script type="uml.Interface" name="interface" file="<%package.name.replaceAll("\.","/")%>/<%name%>.java"%>

package <%package.name%>;

// <%startUserCode%> for imports in <%name%>
// <%endUserCode%>

<%visibility%> interface <%name%> <%if (generalization) {%>implements <%generalization.implementsInterfaces.sep(", ")%> <%}%>{
	<%for (ownedOperation) {%>
	<%visibility%> <%if (parametresSortie.type == "") {%>void<%}else{%><%parametresSortie.type.name%><%}%> <%name%>(<%parametresEntree.declaration.sep(", ")%>);
	<%}%>
}

<%script type="Generalization" name="implementsInterfaces"%>
<%general.name%>

<%script type="Operation" name="parametresEntree"%>
<%ownedParameter[direction != "return"]%>

<%script type="Operation" name="parametresSortie"%>
<%ownedParameter[direction == "return"]%>