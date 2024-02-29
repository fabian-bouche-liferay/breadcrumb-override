<%@ include file="/init.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry" %>

<%@ taglib uri="http://liferay.com/tld/site-navigation" prefix="liferay-site-navigation" %>

<%

	if(request.getAttribute("customBreadcrumbs") != null) {

%>
	<liferay-site-navigation:breadcrumb
		breadcrumbEntries="<%= (List<BreadcrumbEntry>) request.getAttribute("customBreadcrumbs") %>"
	/>

<%
	} else {
%>

	<liferay-ui:breadcrumb
		ddmTemplateGroupId="<%= siteNavigationBreadcrumbDisplayContext.getDisplayStyleGroupId() %>"
		ddmTemplateKey="<%= siteNavigationBreadcrumbDisplayContext.getDDMTemplateKey() %>"
		showCurrentGroup="<%= siteNavigationBreadcrumbDisplayContext.isShowCurrentGroup() %>"
		showGuestGroup="<%= siteNavigationBreadcrumbDisplayContext.isShowGuestGroup() %>"
		showLayout="<%= siteNavigationBreadcrumbDisplayContext.isShowLayout() %>"
		showParentGroups="<%= siteNavigationBreadcrumbDisplayContext.isShowParentGroups() %>"
		showPortletBreadcrumb="<%= siteNavigationBreadcrumbDisplayContext.isShowPortletBreadcrumb() %>"
	/>

<%
	}
%>
