package org.lightadmin.core.view.preparer;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.beans.MenuItem;
import org.apache.tiles.context.TilesRequestContext;
import org.lightadmin.core.config.domain.DomainTypeAdministrationConfiguration;
import org.lightadmin.core.config.domain.GlobalAdministrationConfiguration;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;

public class LeftSectionViewPreparer extends ConfigurationAwareViewPreparer {

	@Override
	protected void execute( final TilesRequestContext tilesContext, final AttributeContext attributeContext, final GlobalAdministrationConfiguration configuration ) {
		addAttribute( attributeContext, "menuItems", menuItems( configuration.getManagedDomainTypeConfigurations().values() ) );
	}

	@Override
	protected void execute( final TilesRequestContext tilesContext, final AttributeContext attributeContext, final DomainTypeAdministrationConfiguration configuration ) {
		final String selectedMenuItemName = configuration.getScreenContext().getMenuItemName();
		addAttribute( attributeContext, "selectedMenuItemName", selectedMenuItemName );
	}

	private Collection<MenuItem> menuItems( Collection<DomainTypeAdministrationConfiguration> configurations ) {
		final List<MenuItem> menuItems = newArrayList( transform( configurations, DomainConfigToMenuItemTransformer.INSTANCE ) );

		sort( menuItems, MenuItemComparator.INSTANCE );

		return menuItems;
	}
}