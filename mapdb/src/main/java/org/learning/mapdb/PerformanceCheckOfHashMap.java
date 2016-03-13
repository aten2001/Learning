package org.learning.mapdb;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.VolatileCallSite;
import java.security.AccessController;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.mapdb.DBMaker;

public class PerformanceCheckOfHashMap 
{
	public static void main( String[] args ) throws InterruptedException, IOException
	{

		//test read through put
		File file = File.createTempFile("mapdb-temp","db");
		System.out.println(file.getAbsolutePath());



	}

	private static void testWrite() throws InterruptedException{
		ConcurrentMap<String, byte[]> map = DBMaker.newTempHashMap();
		byte[] payload = mockPayload();
		Set<String> keys = generateUniqueKeys(10000);

		//test write through put
		int retry = 1000;
		int concurrency = 1000;
		ExecutorService exec = Executors.newFixedThreadPool(concurrency);
		long start = System.currentTimeMillis();
		AtomicInteger count = new AtomicInteger(0);
		while(retry-- > 0){
			exec.execute(() -> {
				for(String key : keys){
					map.put(key, payload);
				}
				count.incrementAndGet();
			});
		}

		while(count.get() != 1000){
			System.out.println(count.get());
			Thread.sleep(200);
		}
		System.out.println(System.currentTimeMillis()-start);
	}


		private static Set<String> generateUniqueKeys(int size) {
			Set<String> set = new HashSet<>(size);
			for(int i=0; i<size; i++){
				set.add(UUID.randomUUID().toString());
			}
			return set;
		}

		public static byte[]  mockPayload(){
			return "{\"id\":\"OD2028337006198724\",\"dataFlow\":{\"name\":\"checkoutFlowV1\",\"description\":\"checkout flow with svcCartResponse changes\",\"targetData\":\"ORDER_COMPLETED_DATA\",\"resolutionSpecs\":{\"PAYMENT_OPTIONS_DATA\":\"PAYMENT_OPTIONS_BUILDER\"},\"executionGraph\":{\"dependencyHierarchy\":[[{\"consumes\":[\"ADDRESS_LIST_DATA\",\"CHANNEL_DATA\",\"CART_COMPLETE_RESPONSE\",\"CART_REF\",\"ACCOUNT_INFO_DATA\"],\"produces\":\"OMS_ORDER\",\"name\":\"ORDER_DATA_BUILDER\",\"rank\":10}],[{\"consumes\":[\"ORDER_PERSISTED\",\"CHANNEL_DATA\",\"OMS_PAYMENTS\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\"],\"produces\":\"ORDER_PERSISTED\",\"name\":\"OMS_CREATE_ORDER_BUILDER\",\"rank\":9}],[{\"consumes\":[\"ORDER_PERSISTED\",\"CHANNEL_DATA\",\"OMS_PAYMENTS\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\",\"ACCOUNT_INFO_DATA\"],\"produces\":\"PAYMENT_OPTIONS_DATA\",\"name\":\"PAYMENT_OPTIONS_BUILDER\",\"rank\":8}],[{\"consumes\":[\"ORDER_PERSISTED\",\"SELECTED_PAYMENT_DATA\",\"CHANNEL_DATA\",\"PAYMENT_OPTIONS_DATA\",\"CART_REF\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\"],\"produces\":\"INTEGRATION_LOGIC_DATA\",\"name\":\"INTEGRATION_LOGIC_BUILDER\",\"rank\":7}],[{\"consumes\":[\"SELECTED_PAYMENT_DATA\",\"ORDER_PERSISTED\",\"CHANNEL_DATA\",\"INTEGRATION_LOGIC_DATA\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\"],\"produces\":\"INITIATED_PAYMENTS\",\"name\":\"OMS_ADD_PAYMENT_BUILDER\",\"rank\":6}],[{\"consumes\":[\"ENCRYPTED_PAYMENT_DATA\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\"],\"produces\":\"DECRYPTED_PAYMENT_RESPONSES\",\"name\":\"DECRYPT_PAYMENT_BUILDER\",\"rank\":5}],[{\"consumes\":[\"INITIATED_PAYMENTS\",\"OMS_ORDER\",\"DECRYPTED_PAYMENT_RESPONSES\"],\"produces\":\"OMS_PAYMENTS\",\"name\":\"OMS_PAYMENT_GENERATOR_BUILDER\",\"rank\":4}],[{\"consumes\":[\"OMS_PAYMENTS\",\"OMS_ORDER\",\"DECRYPTED_PAYMENT_RESPONSES\"],\"produces\":\"PAYMENT_SUMMARY\",\"name\":\"PAYMENT_SUMMARY_BUILDER\",\"rank\":3}],[{\"consumes\":[\"ORDER_PERSISTED\",\"CHANNEL_DATA\",\"PAYMENT_SUMMARY\",\"OMS_PAYMENTS\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\"],\"produces\":\"PAYMENT_PERSISTED\",\"name\":\"OMS_UPDATE_PAYMENT_BUILDER\",\"rank\":2}],[{\"consumes\":[\"TOKEN_DATA\",\"PAYMENT_PERSISTED\",\"ACCOUNT_INFO_DATA\"],\"produces\":\"TOKEN_VALIDATION_DATA\",\"name\":\"TOKEN_VALIDATION_BUILDER\",\"rank\":1}],[{\"consumes\":[\"ADDRESS_LIST_DATA\",\"CHANNEL_DATA\",\"PAYMENT_PERSISTED\",\"PAYMENT_SUMMARY\",\"OMS_PAYMENTS\",\"CART_REF\",\"INITIATED_PAYMENTS\",\"OMS_ORDER\",\"TOKEN_VALIDATION_DATA\"],\"produces\":\"ORDER_COMPLETED_DATA\",\"name\":\"FULFILLMENT_BUILDER\",\"rank\":0}]]},\"transients\":[\"SELECTED_PAYMENT_DATA\",\"CART_COMPLETE_RESPONSE\",\"ENCRYPTED_PAYMENT_DATA\",\"INTEGRATION_LOGIC_DATA\",\"DECRYPTED_PAYMENT_RESPONSES\"],\"enabled\":true,\"loopingEnabled\":true},\"dataSet\":{\"availableData\":{\"ORDER_PERSISTED\":{\"data\":\"ORDER_PERSISTED\",\"orderPersisted\":false},\"ADDRESS_LIST_DATA\":{\"data\":\"ADDRESS_LIST_DATA\",\"addressInfoList\":{}},\"CHANNEL_DATA\":{\"data\":\"CHANNEL_DATA\",\"salesChannel\":\"WEB\",\"createdBy\":\"website\",\"terminalId\":\"TI140963894415796411765135842304433271239200278444496915315872170875\",\"userAgent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36\",\"userIp\":\"122.178.207.26\",\"sessionId\":\"SIE42E23232B1F46CDBDB8E3605109A4A4\",\"affiliateInfo\":\"flipkart\"},\"OMS_PAYMENTS\":{\"data\":\"OMS_PAYMENTS\",\"bankPayments\":{},\"egvPayments\":{},\"walletPayments\":{},\"codPayments\":{}},\"CART_REF\":{\"data\":\"CART_REF\",\"cartId\":\"7AE244E7-8997-4F19-B88E-F70A0BB21820:P:C-0012\",\"cartDomain\":\"physical\",\"version\":1431481691200,\"userAgent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36\"},\"INITIATED_PAYMENTS\":{\"data\":\"INITIATED_PAYMENTS\",\"allPayments\":{},\"bankPayments\":{},\"egvPayments\":{},\"codPayments\":{},\"walletPayments\":{}},\"CLIENT_ACCOUNT_INFO_DATA\":{\"data\":\"CLIENT_ACCOUNT_INFO_DATA\",\"accountId\":\"ACC13508361168176869\",\"pincode\":0,\"emailId\":\"gokulvanan@gmail.com\",\"billToPartyId\":\"ACC13508361168176869\",\"addressCount\":0,\"type\":\"LOGGED_IN\",\"defaultAddress\":false},\"OMS_ORDER\":{\"data\":\"OMS_ORDER\",\"orderId\":\"OD2028337006198724\",\"tokenId\":\"4264057077e4bdc2e79c8a5b0b7bb587\",\"createdDate\":1431481691212,\"createdBy\":\"website\",\"orderPreSoftReserved\":false,\"billingInfo\":{\"billingAmount\":1198.0,\"itemTotalAmount\":1198.0,\"currencyTypeEnum\":\"INR\"},\"vasData\":{\"giftWrap\":{\"hasCustomerOpted\":false,\"giftWrapCharges\":0,\"giftWrapAvailable\":true}},\"buyerInfo\":{\"smsNotifyNumber\":\"\",\"emailId\":\"gokulvanan@gmail.com\",\"billToPartyId\":\"ACC13508361168176869\",\"addressCount\":0,\"type\":\"LOGGED_IN\",\"sessionId\":\"SIE42E23232B1F46CDBDB8E3605109A4A4\",\"privileges\":[\"FlipkartFirst\"]},\"orderItems\":[{\"id\":\"OI300028337006212726\",\"listingId\":\"LSTWCWDTHE7HGFRDDCGIM8L42\",\"listingType\":\"current\",\"itemType\":\"PHYSICAL\",\"quantity\":1,\"sellingPrice\":1198.0,\"cartItemRefId\":\"LSTWCWDTHE7HGFRDDCGIM8L42:7AE244E7-8997-4F19-B88E-F70A0BB21820:P:C-0012:U:SPCMS\",\"isFreebie\":false,\"santaData\":{\"effectiveDiscount\":0,\"pickupCharge\":0},\"serviceabilityData\":{\"prepaidServiceableDetails\":{\"status\":\"OK\",\"serviceable\":true},\"postpaidServiceableDetails\":{\"status\":\"OK\",\"serviceable\":true},\"locationServiceableDetails\":{\"status\":\"NOT_SERVICEABLE\",\"serviceable\":false},\"noPincodeServiceableDetails\":{\"status\":\"NOT_SERVICEABLE\",\"serviceable\":false},\"prepaidServiceable\":true,\"postpaidServiceable\":true,\"locationServiceable\":false,\"noPincodeServiceable\":false},\"cmsData\":{\"listPrice\":1198.0,\"mrp\":1599.0,\"sku\":\"SKU0000000000000\",\"sellerData\":{\"sellerId\":\"wsr\",\"sourcePincode\":\"560026\"},\"productId\":\"WCWDTHE7HGFRDDCG\",\"title\":\"Levi's Men Brown Genuine Leather Wallet\",\"minTitle\":\"Levi's Men Brown Genuine Leather Wallet\",\"categoryId\":\"21274\",\"actualProductId\":\"LSTWCWDTHE7HGFRDDCGIM8L42\",\"vertical\":\"wallet_card_wallet\",\"returnPolicies\":[\"30_day_replacement\"],\"serviceProfile\":\"FBF\",\"listingState\":\"current\",\"isLarge\":false,\"priceValues\":{\"national_shipping_fee_from_buyer\":{\"amount\":0.0,\"currency\":\"INR\"},\"mrp\":{\"amount\":1599.0,\"currency\":\"INR\"},\"flipkart_selling_price\":{\"amount\":1198.0,\"currency\":\"INR\"},\"local_shipping_fee_from_buyer\":{\"amount\":0.0,\"currency\":\"INR\"},\"zonal_shipping_fee_from_buyer\":{\"amount\":0.0,\"currency\":\"INR\"}},\"isDangerousGood\":false,\"procurementSLA\":1},\"adjustmentData\":{\"adjustmentList\":[]},\"metaData\":{},\"reservationInfoOptional\":{\"present\":false},\"itemPromise\":{\"availablePromises\":{\"promiseItemMap\":{\"EXPRESS\":{\"maxSla\":1,\"minSla\":1,\"sla\":1,\"internalSla\":0,\"deliverBeforeDate\":\"2015-05-13T18:00:00.000+05:30\",\"promisedDate\":\"2015-05-14T23:59:59.000+05:30\",\"serviceable\":true,\"cutOffDate\":\"2015-05-13T15:29:00.000+05:30\",\"serviceCharges\":{\"serviceChargeMap\":{\"OCTROI_CHARGE\":0.0,\"SHIPPING_CHARGE\":0.0}},\"dataBag\":{\"sla\":0,\"cost\":0.0,\"available\":false,\"bookingRefId\":\"OI300028337006212726\"},\"serviceabilityTags\":[\"FlipkartFirst\"],\"scPreferred\":\"MEDIUM\"},\"REGULAR\":{\"maxSla\":2,\"minSla\":2,\"sla\":2,\"internalSla\":0,\"deliverBeforeDate\":\"2015-05-13T20:30:00.000+05:30\",\"promisedDate\":\"2015-05-16T23:59:59.000+05:30\",\"serviceable\":true,\"cutOffDate\":\"2015-05-13T11:30:00.000+05:30\",\"serviceCharges\":{\"serviceChargeMap\":{\"OCTROI_CHARGE\":0.0,\"SHIPPING_CHARGE\":0.0}},\"dataBag\":{\"sla\":0,\"cost\":0.0,\"available\":false},\"serviceabilityTags\":[\"FlipkartFirst\"],\"scPreferred\":\"MEDIUM\"}},\"isPromiseFromFallback\":false},\"selectedPromiseSpeedType\":\"EXPRESS\",\"slottedDeliveryAvailable\":false},\"itemState\":\"BUYABLE\"}],\"orderSantaData\":{\"payZippyData\":\"\"},\"adjustmentData\":{\"adjustmentList\":[]}},\"ACCOUNT_INFO_DATA\":{\"data\":\"ACCOUNT_INFO_DATA\",\"accountId\":\"ACC13508361168176869\",\"firstName\":\"\",\"lastName\":\"\",\"billToPartyId\":\"ACC13508361168176869\",\"type\":\"LOGGED_IN\",\"emailId\":\"gokulvanan@gmail.com\",\"smsNotifyNumber\":\"\"}}},\"mutable\":true,\"updated\":1431481691216,\"checkoutType\":\"PHYSICAL\",\"salesChannel\":\"WEB\"}".getBytes();
		}
	}
