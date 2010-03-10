<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML

import PathUtil
import ImportUtil
%>
<%script type="uml.Class" name="classe" file="<%package.name.toPath() + "/" +name%>.java"%>
package <%package.name%>;
<%gestImport%>
//<%startUserCode%> for imports
import java.util.List;

//<%endUserCode%>


/**
 * @brief
 * @version
 * @author Université de Nantes
 */
public <%if (isAbstract){%>abstract <%}%>class <%name%> <%if (generalization){%>extends <%superClass.name%><%}%> <%if (interfaceRealization){%>implements <%for (interfaceRealization.sep(", ")){%><%interfaces%><%}%><%}%> {
	

	 <%for (attribute[association == null]){%>
	/**  */
	private <%declarationAtt%>

	<%}%>
	<%for (attribute[association != null]){%>
	/**  */
	private <%declarationAsso%>
	<%}%>

	
	public <%name%> () {
		super();
	}
	 	
	<%-- Methodes importées de l'interface --%>
	<%for (interfaceRealization.contract.ownedOperation){%>
	<%methodesInterfaces%>
	<%}%>
	
	<%-- Methodes définies dans la super-classe (si abstraites)--%> 
	 	<%if (superClass.isAbstract){%>	
	 	<%superClass.methodesAbstraites%>
	 	<%}%>
	 	
	<%-- Methodes définies dans la classe --%> 	
	<%for (ownedOperation){%>
	<%methodes%>
	<%}%>
	 
	//GETTER
	<%for (attribute){%><%getter%><%}%>
	
	//SETTER
	<%for (attribute){%><%setter%><%}%>
}


<%-- -------------------------------------------------------------------%>
<%-- --------------------DEBUT DES SCRIPTS -----------------------------%>
<%-- -------------------------------------------------------------------%>


<%script type="Parameter" name="declaration"%>
<%resolveType%> <%name%>

<%script type="Property" name="declarationAtt"%>
<%if (upper == -1){%>List<<%type.name%>> <%name%>s;
<%}else{%>
<%type.name%> <%name%>;<%}%>

<%script type="Property" name="declarationAsso"%>
<%if (upper == -1){%>List<<%type.name%>> <%name%>s;
<%}else{%>
<%type.name%> <%name%>;<%}%>

<%script type="Property" name="getter"%>
<%if (visibility == "public" || visibility == "protected") {%>
	<%visibility%> <%if (upper == -1){%>List<<%type.name%>> get<%name.toU1Case()%>s() {
		return <%name%>s;
	<%}else{%><%type.name%> get<%name.toU1Case()%>() {
		return <%name%>;
	<%}%>	}
	
<%}%>

<%script type="Property" name="setter"%>
<%if (visibility == "public" || visibility == "protected") {%>
	<%visibility%> <%if (upper == -1){%>void set<%name.toU1Case()%>s(List<<%type.name%>> <%name%>s) {
		this.<%name%>s = <%name%>s;
	<%}else{%>void set<%name.toU1Case()%>(<%type.name%> <%name%>) {
		this.<%name%> = <%name%>;
	<%}%>	}
	
<%}%>

<%script type="InterfaceRealization" name="interfaces"%>
<%contract.name%>

<%script type="Operation" name="methodes"%>
/**
	<%for (ownedParameter[direction == "in"]){%>
	* @param <%name%>
	<%}%>
	<%for (ownedParameter[direction == "return"]){%>
	* @return <%resolveType%>
	<%}%>
	*/
 <%if (isAbstract){%>	
	<%visibility%> abstract <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].declaration.sep(", ")%>);<%}else{%>
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].declaration.sep(", ")%>) {
		//<%startUserCode%> for <%name%> method body
		//TODO
		<%if (ownedParameter[direction == "return"]){%>
		return null;
		<%}%>
		//<%endUserCode%>
	}<%}%>
	
<%script type="Operation" name="methodesInterfaces"%>
/**
	<%for (ownedParameter[direction == "in"]){%>
	* @param <%name%>
	<%}%>
	<%for (ownedParameter[direction == "return"]){%>
	* @return <%resolveType%>
	<%}%>
	*/
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].declaration.sep(", ")%>) {
		//<%startUserCode%> for <%name%> method body
		//TODO
		<%if (ownedParameter[direction == "return"]){%>
		return null;
		<%}%>
		//<%endUserCode%>
	}
	
	<%script type="Class" name="methodesAbstraites"%>

	 	<%for (ownedOperation){%>
	 	<%if (isAbstract){%>
	<%visibility%> <%ownedParameter[direction == "return"].resolveType%> <%name%>(<%ownedParameter[direction == "in"].declaration.sep(", ")%>) {
		//<%startUserCode%> for <%name%> method body
		//TODO
		<%if (ownedParameter[direction == "return"]){%>
		return null;
		<%}%>
		//<%endUserCode%>	
	}
	 	<%}%>
	 	<%}%>
	 	
<%script type="Class" name="gestImport"%>

<%-- import de la superclasse --%>
<%if (superClass != null && superClass.package.name != package.name){%>
<%(superClass.package.name+"."+superClass.name).addToImport()%>
<%}%>
<%-- import des interfaces --%>
<%for (interfaceRealization){%>
<%(contract.package.name+"."+contract.name).addToImport()%>
<%}%>
<%-- import des types des attributs --%>
<%for (attribute){%>
<%if (type.package.name != "UMLPrimitiveTypes"){%>
<%(type.package.name+"."+type.name).addToImport()%>
<%}%><%}%>
<%-- import des types des signatures de méthodes --%>
<%for (ownedOperation){%>
<%for (ownedParameter){%>
<%if (type.package.name != "UMLPrimitiveTypes"){%>
<%(type.package.name+"."+type.name).addToImport()%>
<%}%><%}%><%}%>
<%getImports()%>

<%script type="Parameter" name="resolveType"%>
<%if (upper == -1){%>List<<%type.name%>><%}else{%><%type.name%><%}%>
