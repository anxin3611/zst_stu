package com.zst.spring.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/23 12:30
 * @description ：工具类
 */
public class EbayUtils {

    /**
     * 获取ebay接口的scope列表
     *
     * @return
     */
    public static List<String> scopes(String type) {

        List<String> scopes = new ArrayList<>();
        String s;
        if (StringUtils.isNotBlank(type) && "SINGLE".equals(type)) {
            s = "https://api.ebay.com/oauth/api_scope\tView public data from eBay\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.guest.order\tPurchase eBay items anywhere without signing in to eBay\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.item.feed\tView curated feeds of eBay items\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.marketing\tRetrieve eBay product and listing data for use in marketing merchandise to buyers\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.product.feed\tView curated feeds of products from the eBay catalog\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.marketplace.insights\tView historical sales data to help buyers make informed purchasing decisions\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.proxy.guest.order\tPurchase eBay items anywhere, using an external vault for PCI compliance";
        } else {
            s = "https://api.ebay.com/oauth/api_scope\tView public data from eBay\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.order.readonly\tView your order details\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.guest.order\tPurchase eBay items anywhere without signing in to eBay\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.marketing.readonly\tView your eBay marketing activities, such as ad campaigns and listing promotions\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.marketing\tView and manage your eBay marketing activities, such as ad campaigns and listing promotions\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.inventory.readonly\tView your inventory and offers\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.inventory\tView and manage your inventory and offers\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.account.readonly\tView your account settings\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.account\tView and manage your account settings\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.fulfillment.readonly\tView your order fulfillments\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.fulfillment\tView and manage your order fulfillments\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.analytics.readonly\tView your selling analytics data, such as performance reports\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.marketplace.insights.readonly\tView product selling data to help you make pricing and stocking decisions\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.catalog.readonly\tSearch and view eBay product catalog information\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.shopping.cart\tView and manage your shopping cart for eBay items\n" +
                    "https://api.ebay.com/oauth/api_scope/buy.offer.auction\tView and manage bidding activities for auctions\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.readonly\tView a user's basic information, such as username or business account details, from their eBay member account\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.email.readonly\tView a user's personal email information from their eBay member account.\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.phone.readonly\tView a user's personal telephone information from their eBay member account\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.address.readonly\tView a user's address information from their eBay member account\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.name.readonly\tView a user's first and last name from their eBay member account\n" +
                    "https://api.ebay.com/oauth/api_scope/commerce.identity.status.readonly\tView a user's eBay member account status\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.finances\tView and manage your payment and order information to display this information to you and allow you to initiate refunds using the third party application\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.item.draft\tView and manage your item drafts\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.payment.dispute\tView and manage disputes and related details (including payment and order information)\n" +
                    "https://api.ebay.com/oauth/api_scope/sell.item\tView and manage your item information";
        }
        String[] split = s.split("\n");
        for (String s1 : split) {
            String[] split1 = s1.split("\t");
            scopes.add(split1[0]);
        }
        return scopes;
    }
}
