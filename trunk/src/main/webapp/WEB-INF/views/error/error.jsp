<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="error-container">
			<h1>500 Internal Error</h1>
			<p>
				Couldn't retrieve the HTML document because of server-configuration
				problems. Contact site administrator.
			</p>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>