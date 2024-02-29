package com.liferay.samples.fbo.breadcrumb.portlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.BreadcrumbUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
	   property = {
	      "javax.portlet.name=com_liferay_site_navigation_breadcrumb_web_portlet_SiteNavigationBreadcrumbPortlet",
	      "service.ranking:Integer=100"
	   },
	   service = PortletFilter.class
	)
public class BreadcrumbPortletFilter implements RenderFilter {

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException {

		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);
		
		List<Integer> breadcrumbEntryTypes = new ArrayList<>();

		// You can decide which elements you'd like to keep in the breadcrumbs
		// Just comment out the ones you don't need
		breadcrumbEntryTypes.add(BreadcrumbUtil.ENTRY_TYPE_CURRENT_GROUP);
		breadcrumbEntryTypes.add(BreadcrumbUtil.ENTRY_TYPE_GUEST_GROUP);
		breadcrumbEntryTypes.add(BreadcrumbUtil.ENTRY_TYPE_LAYOUT);
		breadcrumbEntryTypes.add(BreadcrumbUtil.ENTRY_TYPE_PARENT_GROUP);
		breadcrumbEntryTypes.add(BreadcrumbUtil.ENTRY_TYPE_PORTLET);
		
		try {
			
			List<BreadcrumbEntry> breadcrumbEntries = BreadcrumbUtil.getBreadcrumbEntries(
				httpServletRequest, ArrayUtil.toIntArray(breadcrumbEntryTypes));
			
			request.setAttribute("customBreadcrumbs", breadcrumbEntries);
			
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}	
		
		chain.doFilter(request, response);
		
	}
	
	private static final Log _log = LogFactoryUtil.getLog(
			BreadcrumbPortletFilter.class);
}