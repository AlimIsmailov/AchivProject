<%@ include file="../../template/default/taglib.jsp"%>
<div class="header">
	<div class="name-container">
		<img alt="null" class="avatar"
			src="https://d13yacurqjgara.cloudfront.net/users/22679/avatars/small/bethany2_bw.jpg?1402332328">
		<p>
			Welcome
			<security:authentication property="name" />
		</p>
	</div>
</div>