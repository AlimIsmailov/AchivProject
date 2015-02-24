
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="error-container">
			<h1>403 Forbidden/Access Denied</h1>
			<p>
				Special permission needed to access the site - a password and/or
				login if it is a registration issue. Other times you may not have
				the proper permissions set up on the server or the site's
				administrator just doesn't want you to be able to access the site.
			</p>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>