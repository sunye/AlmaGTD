<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML

import PathUtil
%>

<%script type="uml.Class" name="default" file="<%package.name.toPath()%>/<%name%>.java"%>

<%if (package.length()>0){%>package <%package.name%>;
<%}%>

<%if (attribute[upper == -1].nSize() > 0){%>
import java.util.Collection;
<%if (!isAbstract){%>
import java.util.LinkedList;
<%}%>
<%}%>
//Start of user code <%name%>.import

//End of user code

<%-- Generation de la classe --%>
<%if (package.name.endsWith("entities")){%>
//Start of user code <%name%>.annotation

//End of user code
<%}%>
/**
 * Classe <%name%>.
 */
public <%if (isAbstract){%>
abstract <%}%>class <%name%> <%if (superClass.length() > 0){%>
extends <%superClass.name%> <%}%><%if (interfaceRealization.length() > 0){%>
implements <%interfaceRealization.contract.name.sep(", ")%> <%}%>{

<%-- Generation des attributs --%>
<%for (attribute){%>
	<%if (class.package.name.endsWith("entities")){%>
	//Start of user code <%class.name%>.<%name%>.annotation
	
	//End of user code
	<%}%>
	/** <%nameAttribute.toU1Case()%>. */
	<%if (class.isAbstract){%>protected <%}else{%>private <%}%><%declarationAttribute%>;

<%}%>
<%-- Generation du constructeur avec parametres --%>
<%if (!isAbstract){%>
	<%if (package.name.endsWith("entities")){%>
	/** 
	 * Constructeur par defaut.
	 */
	public <%name%>() {<%-- manque peut-être init des listes --%>}
	
	
	<%}%>
	/**
	 * Constructeur.
	<%for (allAttribute[(lower == 1) && !isUnique]){%>
	 * @param <%nameAttribute%> valeur de <%nameAttribute%> dans le <%class.name%> construit
	<%}%>
	 */
	public <%name%>(<%paramConst%>) {
		<%for (allAttribute[isMultivalued()]){%>
		this.<%nameAttribute%> = new LinkedList<<%type.name%>>();
		<%}%>
		<%for (allAttribute[(lower == 1) && !isUnique]){%>
		<%if (!isMultivalued()){%>
		this.<%nameAttribute%> = <%nameAttribute%>;
		<%}else{%>
		this.<%nameAttribute%>.add(<%nameAttribute%>);
		<%}%>
		<%}%>
	}
			
<%}%>
<%-- Generation des getters et setters --%>
<%for (attribute){%>
	<%if (!name.matches("id")){%>
	/**
	 * Getter de l'attribut <%nameAttribute%>.
	 * @return <%nameAttribute%> 
	 */
	<%visibility%> <%typeAttribute%> get<%nameAttribute.toU1Case()%>() {
		return this.<%nameAttribute%>;
	}
	<%}%>	
	
	<%if (!isReadOnly){%>
	/**
	 * Setter de l'attribut <%nameAttribute%>.
	 * @param <%nameAttribute%> 
	 */
	<%visibility%> void set<%nameAttribute.toU1Case()%>(<%declarationAttribute%>) {
		this.<%nameAttribute%> = <%nameAttribute%>;
	}
	
	<%}%>
<%}%>
<%-- Generation des methode d'ajout et modification pour les attributs a multiplicite superieure a 1 --%>
<%for (attribute[isMultivalued()]){%>
	/**
	 * Methode permettant d'ajouter des <%name%>.
	 */
	public void add<%name.toU1Case()%>(<%type.name%> <%type.name.toL1Case()%>){
		this.<%nameAttribute%>.add(<%type.name.toL1Case()%>);
	}
	
	/**
	 * Methode permettant de supprimer des <%name%>.
	 */
	public void remove<%name.toU1Case()%>(<%type.name%> <%type.name.toL1Case()%>){
		this.<%nameAttribute%>.remove(<%type.name.toL1Case()%>);
	}	
	
<%}%>
<%-- Generation des methodes --%>
<%for (ownedOperation){%>
	/**
	 * Methode <%name%>.
	 */
	<%if (isAbstract){%>
	<%visibility%> abstract <%if (type != null){%><%type.name%><%}else{%>void<%}%> <%name%>(<%ownedParameter[direction == "in"].declaration2.sep(", ")%>);
	<%}else{%>
	<%visibility%> <%if (isStatic){%>static <%}%><%if (type != null){%><%type.name%><%}else{%>void<%}%> <%name%>(<%ownedParameter[direction == "in"].declaration2.sep(", ")%>){
		//Start of user code <%class.name%>.<%name%>
		
		//End of user code
	}
	<%}%>
	
<%}%> 	
<%for (interfaceRealization){%>
	<%for (contract.ownedOperation){%> 	
 	/**
 	 * Methode <%name%> de l'interface <%interface.name%>.
 	 */
 	<%visibility%> <%if (type != null){%><%type.name%><%}else{%>void<%}%> <%name%>(<%ownedParameter[direction == "in"].declaration2.sep(", ")%>){
 	//Start of user code <%interface.name%>.<%name%>
		
	//End of user code
 	}
 	
 	<%}%> 	
<%}%>
<%if (!isAbstract){%>
<%for (abstractSuperClass){%>
	<%for (ownedOperation[isAbstract]){%>
	/**
 	 * Methode <%name%>.
 	 */
 	@Override
	<%visibility%> <%if (type != null){%><%type.name%><%}else{%>void<%}%> <%name%>(<%ownedParameter[direction == "in"].declaration2.sep(", ")%>){
 	//Start of user code <%name%>
		
	//End of user code
 	}
 	
	<%}%>	
<%}%>
<%}%>
}

<%-- Generation des interfaces --%>
<%script type="uml.Interface" name="default" file="<%package.name.toPath()%>/<%name%>.java"%>

<%if (package.length()>0){%>
package <%package.name%>;
<%}%>

//Start of user code <%name%>.import
		
//End of user code
	
/**
 * interface <%name%>
 */
public interface <%name%> <%if (redefinedInterface.length()>0){%> extends <%redefinedInterface.name.sep(", ")%><%}%> {

<%for (ownedOperation){%>
	<%visibility%> <%if (type != null){%><%type.name%><%}else{%>void<%}%> <%name%> (<%ownedParameter[direction == "in"].declaration2.sep(", ")%>);
<%}%>

}


<%script type="uml.Enumeration" name="default" file="<%package.name.toPath()%>/<%name%>.java"%>
<%if (package.length()>0){%>
package <%package.name%>;
<%}%>
/**
 * enumeration <%name%>
 */
public enum <%name%> {
<%ownedLiteral.name.sep(", ")%>
}


<%script type="Property" name="upfirst"%>
<%association.memberEnd.nFirst().upper%>

<%script type="Property" name="uplast"%>
<%association.memberEnd.nLast().upper%>

<%script type="Property" name="nameAttribute"%>
<%if (metamodel::isComposite){%><%association.memberEnd[metamodel::isComposite].name%><%}else{%><%name%><%}%>

<%script type="Property" name="typeAttribute"%>
<%if (!isMultivalued()){%><%type.name%><%}else{%>Collection<<%type.name%>><%}%>

<%script type="Property" name="declarationAttribute"%>
<%typeAttribute%> <%nameAttribute%>

<%script type="Class" name="allAttribute"%>
<%attribute || superClass.allAttribute%>

<%script type="Class" name="paramConst"%>
<%attribute[(lower == 1) && !isUnique].declaration.sep(", ")%><%if ((attribute[(lower == 1) && !isUnique].length()>0) && (superClass.attribute[(lower == 1) && !isUnique].length()>0)){%>, <%superClass.paramConst%><%}else{%><%superClass.paramConst%><%}%>

<%script type="Class" name="abstractSuperClass"%>
<%superClass[isAbstract] || superClass[isAbstract].abstractSuperClass%>
	    
<%script type="Property" name="declaration"%>
<%type.name%> <%name%>

<%script type="Parameter" name="declaration2"%>
<%type.name%> <%name%>