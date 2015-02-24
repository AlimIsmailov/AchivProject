<%@ page language="java" session="true" contentType="text/html; charset=utf8"
   pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<link href="<c:url value="/resources/css/search-edit-users.css" />" rel="stylesheet">

<tiles:insertDefinition name="defaultTemplate">
   <tiles:putAttribute name="body">
      <div class="body">
         <security:authorize access="hasRole('ROLE_ADMIN')">
            <form:form action="searchUsers" commandName="searchRequestForm" method="post">
               <div class="panel panel-default main-container">
                  <div class="panel-body">
                     <div class="row">
                        <div id="NameFieldContainer" class="col-md-5">
                           <form:input id="NameField" path="firstNameOrLoginOrEmail" value="${searchRequestForm.firstNameOrLoginOrEmail}" type="text" class="form-control" placeholder="Name" />
                        </div>
                        <div id="SurNameFieldContainer" class="col-md-5">
                           <form:input id="SurNameField" type="text" path="surName" class="form-control" placeholder="Surname" value="${searchRequestForm.surName}"/>
                        </div>
                        <div class="col-md-2">
                           <div>
                              <button type="submit" class="btn btn-primary col-md-12">
                                 <spring:message code="label.search"/>
                              </button>
                           </div>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-4" >
                           <div class="radio">
                              <label>
                                 <form:radiobutton id="byNameRadioBtn" path="searchTarget" value="searchByName" onClick="toggleRadioButton()" checked="true"/>
                                 <spring:message code="label.name"/>
                              </label>
                              <label>
                                 <form:radiobutton id="byLoginBtn" path="searchTarget" value="searchByLogin" onClick="toggleRadioButton()"/>
                                 <spring:message code="label.loginWord"/>
                              </label>
                              <label>
                                 <form:radiobutton id="byEmailBtn" path="searchTarget" value="searchByEmail" onClick="toggleRadioButton()"/>
                                 <spring:message code="label.email"/>
                              </label>
                           </div>
                        </div>
                        <div class="col-md-8"  >
                           <div class="checkbox inline">
                              <form:checkbox id="advancedOptionsBtn" path="advancedMode" value="1" onClick="toggleButoonSearchOptions()"/>
                              <spring:message code="label.advancedSettings"/>
                           </div>
                        </div>
                     </div>
                     <div id="SearchingOptionsContainer" class="row top-buffer hide">
                        <div class="col-md-4">
                           <div class="panel panel-primary">
                              <div class="panel-heading">
                                 <div class="row">
                                    <div class="col-md-7"  >
                                       <h3 class="panel-title">
                                          <spring:message code="label.role"/>
                                       </h3>
                                    </div>
                                    <div class="col-md-3">
                                       <form:select class="selectOperationOnRoles selectpicker" path="operationOnRoles">
                                          <option value="AND" 
                                          <c:if test="${searchRequestForm.operationOnRoles == 'AND'}">selected</c:if>
                                          >AND</option>
                                          <option value="OR" 
                                          <c:if test="${searchRequestForm.operationOnRoles == 'OR'}">selected</c:if>
                                          >OR</option>
                                       </form:select>
                                    </div>
                                    <div class="col-md-2"  >
                                    </div>
                                 </div>
                              </div>
                              <div class="panel-body">
                                 <div class="controls">
                                    <form:checkbox path="selectedRoles" value="ROLE_ADMIN"/>
                                    <spring:message code="label.role.admin"/>
                                    <br>
                                    <form:checkbox path="selectedRoles" value="ROLE_MANAGER"/>
                                    <spring:message code="label.role.manager"/>
                                    <br>
                                    <form:checkbox path="selectedRoles" value="ROLE_STUDENT"/>
                                    <spring:message code="label.role.student"/>
                                    <br>
                                    <form:checkbox path="selectedRoles" value="ROLE_USER"/>
                                    <spring:message code="label.role.user"/>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4">
                           <div class="panel panel-primary">
                              <div class="panel-heading">
                                 <h3 class="panel-title">
                                    <spring:message code="label.status"/>
                                 </h3>
                              </div>
                              <div class="panel-body">
                                 <div class="radio">
                                    <label>
                                       <form:radiobutton  path="statusOption" value="1"/>
                                       <spring:message code="label.active"/>
                                    </label>
                                    <br>
                                    <label>
                                       <form:radiobutton  path="statusOption" value="0"/>
                                       <spring:message code="label.notActive"/>
                                    </label>
                                    <br>
                                    <label>
                                       <form:radiobutton  path="statusOption" value="any"/>
                                       <spring:message code="label.anyStatus"/>
                                    </label>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <div class="col-md-4">
                           <div class="panel panel-primary">
                              <div class="panel-heading">
                                 <h3 class="panel-title">
                                    <spring:message code="label.tableColumns"/>
                                 </h3>
                              </div>
                              <div class="panel-body">
                                 <form:checkbox path="firstNameColumn" value="1"/>
                                 <spring:message code="label.firstname"/>
                                 <br>
                                 <form:checkbox path="lastNameColumn" value="1"/>
                                 <spring:message code="label.lastname"/>
                                 <br>
                                 <form:checkbox path="loginColumn" value="1"/>
                                 <spring:message code="label.loginWord"/>
                                 <br>
                                 <form:checkbox path="emailColumn" value="1"/>
                                 <spring:message code="label.email"/>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </form:form>
            <br>
            <div class="row">
               <div class="col-md-4 text-left">
                  <spring:message code="label.found"/>
                  : ${userListForm.listSize};
                  <c:if test="${userListForm.listSize != 0}">
                     <spring:message code="label.page"/>
                     : ${userListForm.page} 
                     <spring:message code="label.page.of"/>
                     ${userListForm.totalPagesCount}
                  </c:if>
               </div>
            </div>
            <c:if test="${userListForm.userList != null}">
               <c:if test="${userListForm.listSize != 0}">
                  <form:form method="post" action="applyChanges" commandName="userListForm">
                     <table class="table table-bordered table-hover table-striped">
                        <tr>
                           <c:if test="${searchRequestForm.firstNameColumn == true}">
                              <th rowspan="2">
                                 <spring:message code="label.firstname"/>
                              </th>
                           </c:if>
                           <c:if test="${searchRequestForm.lastNameColumn == true}">
                              <th rowspan="2">
                                 <spring:message code="label.lastname"/>
                              </th>
                           </c:if>
                           <c:if test="${searchRequestForm.emailColumn == true}">
                              <th rowspan="2">
                                 <spring:message code="label.email"/>
                              </th>
                           </c:if>
                           <c:if test="${searchRequestForm.loginColumn == true}">
                              <th rowspan="2">
                                 <spring:message code="label.loginWord"/>
                              </th>
                           </c:if>
                           <th class="text-center" colspan="3">
                              <spring:message code="label.role"/>
                           </th>
                           <th class="text-center" rowspan="2">
                              <spring:message code="label.active"/>
                           </th>
                        </tr>
                        <tr>
                           <td class="hide">
                              <spring:message code="label.role.user"/>
                           </td>
                           <td class="tg-031e text-center">
                              <spring:message code="label.role.admin"/>
                           </td>
                           <td class="tg-031e text-center">
                              <spring:message code="label.role.manager"/>
                           </td>
                           <td class="tg-031e text-center">
                              <spring:message code="label.role.student"/>
                           </td>
                        </tr>
                        <c:forEach items="${userListForm.pageList}" var="user" varStatus="status">
                           <tr>
                              <td class="hide">
                                 <form:input id="tableRow" path="pageList[${status.index}].id"/>
                              </td>
                              <c:if test="${searchRequestForm.firstNameColumn == true}">
                                 <td>
                                    <input type="text" class="readOnlyRow" name="pageList[${status.index}].firstName" value="${user.firstName}" readonly/>
                                 </td>
                              </c:if>
                              <c:if test="${searchRequestForm.lastNameColumn == true}">
                                 <td><input type="text" class="readOnlyRow" name="pageList[${status.index}].lastName" value="${user.lastName}" readonly/>
                                 </td>
                              </c:if>
                              <c:if test="${searchRequestForm.emailColumn == true}">
                                 <td><input type="text" class="readOnlyRow" name="pageList[${status.index}].email" value="${user.email}" readonly/>
                                 </td>
                              </c:if>
                              <c:if test="${searchRequestForm.loginColumn == true}">
                                 <td><input type="text" class="readOnlyRow" name="pageList[${status.index}].login" value="${user.login}" readonly/>
                                 </td>
                              </c:if>
                              <td class="hide">
                                 <form:checkbox path="pageList[${status.index}].roles" value="ROLE_USER"/>
                              </td>
                              <td class="text-center">
                                 <form:checkbox path="pageList[${status.index}].roles" value="ROLE_ADMIN"/>
                              </td>
                              <td class="text-center">
                                 <form:checkbox path="pageList[${status.index}].roles" value="ROLE_MANAGER"/>
                              </td>
                              <td class="text-center">
                                 <form:checkbox path="pageList[${status.index}].roles" value="ROLE_STUDENT"/>
                              </td>
                              <td class="text-center">
                                 <form:checkbox path="pageList[${status.index}].active" value="1"/>
                              </td>
                           </tr>
                        </c:forEach>
                     </table>
                     <div class="row">
                        <div class="col-md-8">
                           <c:if test="${userListForm.listSize > userListForm.elementsPerPage}">
                              <ul class="pagination"  style="margin: 0;">
                                 <li ><a href="previousPage">&laquo;</a></li>
                                 <li><a href="nextPage">&raquo;</a></li>
                              </ul>
                           </c:if>
                        </div>
                        <div  class="col-md-4 text-right">
                           <button type="submit" class="btn  btn-primary">
                           <spring:message code="label.apply"/>
                           </button>
                        </div>
                     </div>
                  </form:form>
               </c:if>
            </c:if>
            <script src="
            <c:url value="/resources/js/search-edit-users.js" />
            "></script>
         </security:authorize>
      </div>
   </tiles:putAttribute>
</tiles:insertDefinition>