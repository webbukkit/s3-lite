package io.github.linktosriram.s3lite.api.region;

import java.net.URI;
import java.util.HashMap;
import java.util.stream.Stream;

import static java.net.URI.create;

/**
 * Reference: https://docs.aws.amazon.com/general/latest/gr/rande.html#s3_region
 */
public class Region {
	private static HashMap<String, Region> regionsByName = new HashMap<String, Region>();

	public static Region US_EAST_1 = new Region("us-east-1", create("https://s3.us-east-1.amazonaws.com")),
			US_EAST_2 = new Region("us-east-2", create("https://s3.us-east-2.amazonaws.com")),
			US_WEST_1 = new Region("us-west-1", create("https://s3.us-west-1.amazonaws.com")),
			US_WEST_2 = new Region("us-west-2", create("https://s3.us-west-2.amazonaws.com")),

			AF_SOUTH_1 = new Region("af-south-1", create("https://s3.af-south-1.amazonaws.com")),
			
			AP_EAST_1 = new Region("ap-east-1", create("https://s3.ap-east-1.amazonaws.com")),
			AP_SOUTH_1 = new Region("ap-south-1", create("https://s3.ap-south-1.amazonaws.com")),
			AP_SOUTH_2 = new Region("ap-south-2", create("https://s3.ap-south-2.amazonaws.com")),
			AP_NORTHEAST_1 = new Region("ap-northeast-1", create("https://s3.ap-northeast-1.amazonaws.com")),
			AP_NORTHEAST_2 = new Region("ap-northeast-2", create("https://s3.ap-northeast-2.amazonaws.com")),
			AP_NORTHEAST_3 = new Region("ap-northeast-3", create("https://s3.ap-northeast-3.amazonaws.com")),
			AP_SOUTHEAST_1 = new Region("ap-southeast-1", create("https://s3.ap-southeast-1.amazonaws.com")),
			AP_SOUTHEAST_2  = new Region("ap-southeast-2", create("https://s3.ap-southeast-2.amazonaws.com")),
			AP_SOUTHEAST_3 = new Region("ap-southeast-3", create("https://s3.ap-southeast-3.amazonaws.com")),
			AP_SOUTHEAST_4  = new Region("ap-southeast-4", create("https://s3.ap-southeast-4.amazonaws.com")),

			EU_WEST_1 = new Region("eu-west-1", create("https://s3.eu-west-1.amazonaws.com")),
			EU_WEST_2 = new Region("eu-west-2", create("https://s3.eu-west-2.amazonaws.com")),
			EU_WEST_3 = new Region("eu-west-3", create("https://s3.eu-west-3.amazonaws.com")),
			EU_CENTRAL_1 = new Region("eu-central-1", create("https://s3.eu-central-1.amazonaws.com")),
			EU_CENTRAL_2 = new Region("eu-central-2", create("https://s3.eu-central-2.amazonaws.com")),
			EU_NORTH_1 = new Region("eu-north-1", create("https://s3.eu-north-1.amazonaws.com")),
			EU_SOUTH_1 = new Region("eu-south-1", create("https://s3.eu-south-1.amazonaws.com")),
			EU_SOUTH_2 = new Region("eu-south-2", create("https://s3.eu-south-2.amazonaws.com")),

			ME_CENTRAL_1 = new Region("me-central-1", create("https://s3.me-central-1.amazonaws.com")),
			ME_SOUTH_1 = new Region("me-south-1", create("https://s3.me-south-1.amazonaws.com")),
			
			CA_CENTRAL_1 = new Region("ca-central-1", create("https://s3.ca-central-1.amazonaws.com")),

			SA_EAST_1 = new Region("sa-east-1", create("https://s3.sa-east-1.amazonaws.com")),
			
			US_GOV_EAST_1 = new Region("us-gov-east-1", create("https://s3.us-gov-east-1.amazonaws.com")),
			US_GOV_WEST_1 = new Region("us-gov-west-1", create("https://s3.us-gov-west-1.amazonaws.com")),

			CN_NORTH_1 = new Region("cn-north-1", create("https://s3.cn-north-1.amazonaws.com.cn")),
			CN_NORTHWEST_1 = new Region("cn-northwest-1", create("https://s3.cn-northwest-1.amazonaws.com.cn"));

	private final String regionName;
	private final URI endpoint;

	Region(final String regionName, final URI endpoint) {
		this.regionName = regionName;
		this.endpoint = endpoint;
		regionsByName.put(regionName, this);
	}

	public String getRegionName() {
		return regionName;
	}

	public URI getEndpoint() {
		return endpoint;
	}

	public static Region fromString(final String regionName) {
		Region r = regionsByName.get(regionName);
		if (r == null) {
			// Assume it might be endpoint URL for custom endpoint
			try {
				URI ep = create(regionName);
				r = new Region(regionName, ep);
			} catch (IllegalArgumentException iax) {
				throw new IllegalArgumentException("No matching region and not valid URL: " + regionName);
			}
		}
		return r;
	}

	@Override
	public String toString() {
		return "Region{" + "regionName='" + regionName + '\'' + ", endpoint=" + endpoint + '}';
	}
}
