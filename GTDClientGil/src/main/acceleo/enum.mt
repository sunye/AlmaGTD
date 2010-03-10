<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML

import PathUtil
%>

<%script type="Enumeration" name="generateEnum" file="<%package.name.toPath() + "/" +name%>.java"%>
package <%package.name%>;

public enum <%name%> {
	<%ownedLiteral.name.sep(",\n")%>
}