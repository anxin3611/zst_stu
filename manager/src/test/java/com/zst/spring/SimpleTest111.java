package com.zst.spring;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:10
 * @description 简单的测试类
 */
public class SimpleTest111 {

    @Test
    public void testSystemCurrentMills() {
        // 毫秒值
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testLength() {
        int length = "v^1.1#i^1#p^3#r^1#I^3#f^0#t^Ul4xMF80OkY2Qjk2OEQ4M0I0QzhFNzAwMjU5ODQ5NkY4QzM4NTQ1XzBfMSNFXjEyODQ=".length();
        System.out.println(length);
    }

    @Test
    public void testLength1() {
        int length = "d7d09ce3865f40ed9c85d0ee5b752758".length();
        System.out.println(length);
    }

    @Test
    public void testStringFormat() {
        long l = System.currentTimeMillis() / 1000;
        System.out.println(l);
        String format = String.format("%010d", l);
        System.out.println(format);
        int i = Integer.parseInt(format);
        System.out.println(i);
        Integer integer = Integer.valueOf(format);
        System.out.println(integer);
    }

    @Test
    public void testHASH() {
        int i1 = "dfadsfsda".hashCode();
        System.out.println(i1);
        int i = "DFDFDSNFDGF".hashCode();
        System.out.println(i);
        System.out.println(File.separator);
        System.out.println("/");
    }

    @Test
    public void test() throws ParseException {
        String datetime = "2020-03-05T12:03:11.182+0000";
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = simpleDateFormat.parse(datetime);


        System.out.println(new Date());
        System.out.println(parse);
    }

    RateLimiter rateLimiter = RateLimiter.create(10);

    @Test
    public void testQueue() {
        ThreadPoolExecutor threadExecutor = new ThreadPoolExecutor(100, 100, 3000, TimeUnit.SECONDS, new PriorityBlockingQueue<>(100), new ThreadFactoryBuilder().build());
        for (int i = 0; i < 100; i++) {
            threadExecutor.execute(this::test111);
        }

    }

    private void test111() {
        boolean b = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
        if (!b) {
            System.out.println("超出范围");
            return;
        }
        System.out.println("可以操作");
    }

    /**
     * v%5E1.1%23i%5E1%23p%5E3%23r%5E1%23I%5E3%23f%5E0%23t%5EUl41Xzk6MDc1ODg4MUM4Njg3ODk3RTBEMDhFMTI1QjEzMUU0RDBfMF8xI0VeMTI4NA%3D%3D
     * v%5E1.1%23i%5E1%23p%5E3%23f%5E0%23r%5E1%23I%5E3%23t%5EUl41Xzk6QzM0Q0JCRDJGOUZBNjRBMDE4QzEwMzY4MjlCNzM2MEZfMF8xI0VeMTI4NA%3D%3D
     */
    @Test
    public void getCode() {
        String str = "https://signin.ebay.com/ws/eBayISAPI.dll?ThirdPartyAuthSucessFailure&isAuthSuccessful=true&code=v%5E1.1%23i%5E1%23f%5E0%23p%5E3%23r%5E1%23I%5E3%23t%5EUl41Xzg6NjQ1REI4MzI5RjU1N0U4REZBNjZGRTU5Mzg2OTEwNzVfMF8xI0VeMTI4NA%3D%3D&expires_in=299";
        String[] split = str.split("&");
        for (String s : split
        ) {
            if (s.startsWith("code")) {
                String[] split1 = s.split("=");
                System.out.println(split1[1]);
            }
        }
    }

    @Test
    public void getRedirectUrl() {
        String str = "https://auth.sandbox.ebay.com/oauth2/consents?client_id=jianli-thisisli-SBX-c69ec6b8e-18115ce9&redirect_uri=jian_li-jianli-thisisli-khphh&scope=https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fbuy.order.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fbuy.guest.order+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.marketing.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.marketing+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.inventory.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.inventory+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.account.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.account+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.fulfillment.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.fulfillment+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.analytics.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.marketplace.insights.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.catalog.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fbuy.shopping.cart+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fbuy.offer.auction+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.email.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.phone.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.address.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.name.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fcommerce.identity.status.readonly+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.finances+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.item.draft+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.payment.dispute+https%3A%2F%2Fapi.ebay.com%2Foauth%2Fapi_scope%2Fsell.item&state&response_type=code&hd&consentGiven=false";
        String[] split = str.split("&");
        for (String s : split
        ) {
            if (s.startsWith("redirect_uri")) {
                String[] split1 = s.split("=");
                System.out.println(split1[1]);
            }
        }

    }

    @Test
    public void test11() {
        String str = "AccessToken{, token='v^1.1#i^1#p^3#r^0#I^3#f^0#t^H4sIAAAAAAAAAOVXa2wUVRTu9iUECigGkWhcpkStZXbvPHZ3dmDXbNst3dLH0i1NW4P1zsyddtrZmXXuXdpFkVotxhRCokRJlKRRaDAm+kNMxMT0D4jEPwZREtQfGh/4qApaJZqgM9sH2xoBKZom7p/Ze+65557vO9+dOwf0Fy+8Z2fNzl9LXDfkD/eD/nyXi1kEFhYXlS8pyF9VlAdyHFzD/Wv6CwcKzq7HMKmnxCaEU6aBkbsvqRtYzBpDVNoyRBNiDYsGTCIsEllMROrrRNYDxJRlElM2dcodqwpRKi/4FJ+fCwKJC6q8z7YaUzGbzRAlCSpATEDwSzLHSIzfnsc4jWIGJtAgIYoFLKABR7NcM+BEThD5gIf3B9spdwuysGYatosHUOFsumJ2rZWT6+VThRgji9hBqHAsUp1ojMSqog3N6705scKTPCQIJGk8c1RpKsjdAvU0uvw2OOstJtKyjDCmvOGJHWYGFSNTyVxD+lmqA6oM1aAP+FhBlRDHXBcqq00rCcnl83AsmkKrWVcRGUQjmSsxarMhdSOZTI4a7BCxKrfz2JSGuqZqyApR0YpI2+ZEtIlyJ+Jxy9yqKUhxkDIcz7EBThCoMEHYphBZHdswASzDTW41EW+S6Fl7VZqGojm0YXeDSSqQnTeazQ6bw47t1Gg0WhGVODnl+vmmWeTbnbJO1DFNugynsihpU+HODq9cgylRXJLB9ZKFH7KKxEC/KvOAU31gliycs35N0gg71YnE414nFyTBDJ2EVg8iKR3KiJZtetNJZGmKyPlUlhNURCv+oErzQVWlJZ/ipxkVIYCQJMlB4f+lEEIsTUoTNK2S2RNZmCHKYVXUoCoSswcZzZkUomZ7Zl8/k9LowyGqi5CU6PX29vZ6ejmPaXV6WQAYb2t9XULuQklITftqV3amtaxIZGSvwppI7ARCVJ+tQXtzo5MKN0Wrm6KJmo7mxo3Rhin9zsgsPNv6N0gTSLYQmV/oelqSDyo6iqC2ztqGKpRp29ZcW653J3lvpZzujqagHqit8W70R2pwaG7gZTOF4qauyZl/lwHnrP9TFjhLiUOLZCrSGXucQLpuP+YEFztw51epnfXYDgBTmsc5dB7ZTHpNaL+6HVNHNmP31Th5sU2QZ+JFaEe+ljUeC0HFNPTMnEiOpFKxZDJNoKSjmDK/2Oa4oMCBOcObZ6i6NWjoGk26NPtatv8kKlpp2R9Esl8SEM0IDOOTUXBOqKvQ1qtGXTjgOvEfIYcSw3CsGqBhgGNp+6qTaMEfCNCAB6wvyHMQsr454a7UNfuSnH/3X42JCVKuFtosQ87l/5cvP+/M5iucl/0xA663wIDriN2/gQCgmXJQVlywubBgMYU1gjwYGopk9nnsDwYP1joNu7ewkKcHZVJQs/KLXdqZU/KFnLZveAtYOd34LSxgFuV0geC2SzNFzNJbSmxiOJYDHCfwgXZQemm2kFlRePPOJ5fdOnj+3S+5n4rWtZ1qqts+voAHJdNOLldRnq3HPP+uF8ce8Y8d2tfxSWf7+LA3GJPKSh4N3X14z4FVBSd33Le3Q07mDT4TOPn2w7VfbB3aO1jeOlqwaaT5/JvuodcXvMzvGK3a3XLw/PLfnrizaveHa+/a4Klv3Hz7spFv7uhb8XvHkWOvfT6yb3T1+POZ4IVNz5aN1C54+iLbc++hwtMo9O3BLuVidP9+S/qsPjK0Gq86du65l14dUn4801f9/Yav6ZWreepsWY215dO8daPdg+zxusYGfWlUSo2Xdq5dc9Pic0vW959OP37h6IGP3/hlz9j2h8yndsXu/1l478A7D+yLH+4XXnlhLO9G/rvQD0dLBy8WDT62bs2JP4LC8XP4q9bxug8+Wq6/3xufKOOfYzapP5APAAA=', expiresOn=Mon Mar 23 13:38:48 CST 2020}";
        String[] split = str.split(",");
        for (String s : split
        ) {
            if (s.startsWith("token")) {
                String[] split1 = s.split("=");
                System.out.println(split1[1]);
            }
        }
    }

    @Test
    public void test1() {
        List<String> scopes = new ArrayList<>();
        String s = "https://api.ebay.com/oauth/api_scope\tView public data from eBay\n" +
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

        String[] split = s.split("\n");
        for (String s1 : split) {
            String[] split1 = s1.split("\t");
            scopes.add(split1[0]);
        }
    }
}
