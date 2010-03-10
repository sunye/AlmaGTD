<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
%>

<%script type="uml.Class" name="class" file="<%parent().name.replaceAll("\.","/")%>/<%name%>.java"%>

// fichier <%parent().name.replaceAll("\.","/")%>/<%name%>.java

<%put("maClasse")%>

package <%package.name%>;

// <%startUserCode%> for imports in <%name%>
import java.util.List;
// <%endUserCode%>

// <%startUserCode%> for comments in <%name%>
/**
 * 
 */
// <%endUserCode%>

public <%if (isAbstract) {%>abstract <%}%>class <%name%> <%if (generalization) {%>extends <%superClass.name%><<%name%>> <%}%><%if (interfaceRealization) {%>implements <%interfaceRealization.implementsInterfaces.sep(", ")%> <%}%>{ 

	<%-- Declaration des attributs internes de la classe --%>
	<%for (ownedAttribute) {%>
	<%if (upper == "-1"){%>
	<%typeAttribute%> List<<%type.name%>> <%name%> = new ArrayList<<%type.name%>>();
	<%}else{%>
	<%typeAttribute%> <%type.name%> <%name%>;
	<%}%>
	<%}%>
	
	<%-- Declaration des attributs relations de la classe --%>
	<%for (package.ownedMember[name.startsWith("A_")]) {%>
		<%for (ownedElement) {%>
			<%if (self.type == get("maClasse")) {%>
	<%parent.declarationAttributsAssocies%>
			<%}%>
		<%}%>
	<%}%>
	
	// <%startUserCode%> for attributes in <%name%>
	
	// <%endUserCode%>
	
	<%-- Generation des constructeurs --%>
	// <%startUserCode%> for constructors in <%name%>
	public <%name%>() {
		super();
	}
	
	public <%name%>(<%attribute.declaration.sep(", ")%>) {
		super();
		<%for (attribute) {%>
		this.set<%name.toU1Case()%>(<%name%>);
		<%}%>
	}
	// <%endUserCode%>
	
	<%-- Generation des getters/setters --%>
	<%for (attribute) {%>
	<%visibility%><%if (isStatic) {%> static<%}%> <%type.name%> get<%name.toU1Case()%>() {
		// <%startUserCode%> for getter of attributes in <%name%>
		return <%name%>;
		// <%endUserCode%>
	}
	
	<%if (isReadOnly) {%>
	// attribut readOnly
	private<%}else{%><%visibility%><%}%><%if (isStatic) {%> static<%}%> void set<%name.toU1Case()%>(<%type.name%> <%name%>) {
		// <%startUserCode%> for setter of attributes in <%name%>
		this.<%name%> = <%name%>;
		// <%endUserCode%>
	}
	<%}%>
	
	<%-- Getters et setters sur les attributs en relations de la classe --%>
	<%for (package.ownedMember[name.startsWith("A_")]) {%>
		<%for (ownedElement) {%>
			<%if (self.type == get("maClasse")) {%>
	<%parent.declarationGetSetAttributsAssocies%>
			<%}%>
		<%}%>
	<%}%>
	
	<%-- Generation des operations definies dans le modele --%>
	<%for (ownedOperation){%>
	<%visibility%> <%if (isStatic) {%>static <%}%><%if (parametresSortie.type == "") {%>void<%}else{%><%parametresSortie.type.name%><%}%> <%name%>(<%parametresEntree.declaration.sep(", ")%>) {
		<%startUserCode%> for <%name%> method body <%endUserCode%>
	}
	<%}%>
	
	<%for (interfaceRealization) {%>
		<%for (contract.ownedOperation) {%>
	/**
	 * @see <%interface.name%>#<%name%>
	 */
	<%visibility%> <%if (isStatic) {%>static <%}%><%if (parametresSortie.type == "") {%>void<%}else{%><%parametresSortie.type.name%><%}%> <%name%>(<%parametresEntree.declaration.sep(", ")%>) {
		<%startUserCode%> for <%name%> method body <%endUserCode%>
	}
		<%}%>
	<%}%>
}


<%script type="Parameter" name="declaration"%>
final <%type.name%> <%name%>

<%script type="Property" name="declaration"%>
final <%type.name%> <%name%>

<%script type="Operation" name="parametresEntree"%>
<%ownedParameter[direction != "return"]%>

<%script type="Operation" name="parametresSortie"%>
<%ownedParameter[direction == "return"]%>

<%script type="Property" name="typeAttribute"%>
private<%if (isStatic) {%> static<%}%>

<%script type="InterfaceRealization" name="implementsInterfaces"%>
<%contract.name%>

<%script type="uml.Association" name="declarationAttributsAssocies"%>
<%for (navigableOwnedEnd) {%>
	<%if (upper == "-1") {%>
	private List<<%type.name%>> <%name%>;
	<%}else{%>
	private <%type.name%> <%name%>;
	<%}%>
<%}%>

<%script type="uml.Association" name="declarationGetSetAttributsAssocies"%>
<%for (navigableOwnedEnd) {%>
	<%if (upper == "-1") {%>
<%visibility%> List<<%type.name%>> get<%name.toU1Case()%>() {
	return this.<%name%>;
}

<%visibility%> void set<%name.toU1Case()%>(List<<%type.name%>> <%name%>) {
	this.<%name%> = <%name%>;
}
	<%}else{%>
<%visibility%> <%type.name%> get<%name.toU1Case()%>() {
	return this.<%name%>;
}

<%visibility%> void set<%name.toU1Case()%>(<%type.name%> <%name%>) {
	this.<%name%> = <%name%>;
}		
	<%}%>	
<%}%>