package com.alphatrader.rest;

import com.alphatrader.rest.util.PropertyGson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test case for the {@link UserProfile} class.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0.0
 */
public class UserProfileTest {
    private static HttpResponder httpResponder = HttpResponder.getInstance();
    private static final Gson gson = new PropertyGson().create();

    private static final String JSON = "{\n" +
        "  \"cashTransferLogs\": [\n" +
        "    {\n" +
        "      \"amount\": 1350,\n" +
        "      \"receiverBankAccount\": \"443db0b7-c399-4252-8166-061d8de1d634\",\n" +
        "      \"senderBankAccount\": \"c4d789b2-5e74-4618-a62e-fedc37fc2e4f\",\n" +
        "      \"date\": 1474445689862,\n" +
        "      \"message\": {\n" +
        "        \"message\": \"Salary from Katholische Kirche AG\",\n" +
        "        \"substitutions\": [],\n" +
        "        \"filledString\": \"Salary from Katholische Kirche AG\"\n" +
        "      },\n" +
        "      \"id\": \"a287ba64-f57b-4a5d-902e-c0180e75d15f\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"amount\": 0,\n" +
        "      \"receiverBankAccount\": \"443db0b7-c399-4252-8166-061d8de1d634\",\n" +
        "      \"senderBankAccount\": \"46c83556-f323-4396-b37a-0991cfe50fdc\",\n" +
        "      \"date\": 1474474440471,\n" +
        "      \"message\": {\n" +
        "        \"message\": \"Salary from BoomBangBoom Inc.\",\n" +
        "        \"substitutions\": [],\n" +
        "        \"filledString\": \"Salary from BoomBangBoom Inc.\"\n" +
        "      },\n" +
        "      \"id\": \"e88cbec9-ae5e-40c0-b231-2c0446a96e3c\"\n" +
        "    },\n" +
        "  ],\n" +
        "  \"employments\": [\n" +
        "    {\n" +
        "      \"dailyWage\": 5,\n" +
        "      \"startDate\": 1474474477123,\n" +
        "      \"company\": {\n" +
        "        \"securitiesAccountId\": \"b576e190-d033-4b54-9228-f8662f5b7934\",\n" +
        "        \"securityIdentifier\": \"STB46081\",\n" +
        "        \"ceo\": {\n" +
        "          \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "          \"userCapabilities\": {\n" +
        "            \"level2UserEndDate\": null,\n" +
        "            \"premiumEndDate\": null,\n" +
        "            \"level2User\": false,\n" +
        "            \"partner\": true,\n" +
        "            \"premium\": false,\n" +
        "            \"locale\": null\n" +
        "          },\n" +
        "          \"username\": \"FauserneEist\",\n" +
        "          \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "        },\n" +
        "        \"name\": \"BoomBangBoom Inc.\",\n" +
        "        \"id\": \"50ea9f61-af3d-42a3-9a98-43f7757e3adb\"\n" +
        "      },\n" +
        "      \"id\": \"45c61bd6-4b82-4446-9c0f-a81af052edb5\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"dailyWage\": 36,\n" +
        "      \"startDate\": 1475055321695,\n" +
        "      \"company\": {\n" +
        "        \"securitiesAccountId\": \"4aeadc7b-7c76-4a32-bfe6-c51c5037e747\",\n" +
        "        \"securityIdentifier\": \"STK57F4A\",\n" +
        "        \"ceo\": {\n" +
        "          \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "          \"userCapabilities\": {\n" +
        "            \"level2UserEndDate\": null,\n" +
        "            \"premiumEndDate\": null,\n" +
        "            \"level2User\": false,\n" +
        "            \"partner\": true,\n" +
        "            \"premium\": false,\n" +
        "            \"locale\": null\n" +
        "          },\n" +
        "          \"username\": \"FauserneEist\",\n" +
        "          \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "        },\n" +
        "        \"name\": \"kostolowski Inc.\",\n" +
        "        \"id\": \"cc0bdd92-edbc-446f-be14-8dcb61d3f940\"\n" +
        "      },\n" +
        "      \"id\": \"b8f2317f-5f5b-4bb5-9211-b54931ba0042\"\n" +
        "    },\n" +
        "  ],\n" +
        "  \"initiatedPolls\": [\n" +
        "    {\n" +
        "      \"dailyWage\": 0,\n" +
        "      \"applicant\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"abstentionRule\": \"COUNTS_AS_REFUSAL\",\n" +
        "      \"approvalVotesPercentage\": 0,\n" +
        "      \"company\": {\n" +
        "        \"securityIdentifier\": \"STSB8312\",\n" +
        "        \"listing\": {\n" +
        "          \"startDate\": 1475678348697,\n" +
        "          \"endDate\": null,\n" +
        "          \"securityIdentifier\": \"STSB8312\",\n" +
        "          \"name\": \"randolphduke\",\n" +
        "          \"type\": \"STOCK\"\n" +
        "        },\n" +
        "        \"name\": \"randolphduke\",\n" +
        "        \"id\": \"cb1cbdfe-d23e-42a7-b840-0eec1d00c85f\"\n" +
        "      },\n" +
        "      \"totalNumberOfVoices\": 107576,\n" +
        "      \"votes\": [\n" +
        "        {\n" +
        "          \"voices\": 96576,\n" +
        "          \"voter\": {\n" +
        "            \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "            \"userCapabilities\": {\n" +
        "              \"level2UserEndDate\": null,\n" +
        "              \"premiumEndDate\": null,\n" +
        "              \"level2User\": false,\n" +
        "              \"partner\": true,\n" +
        "              \"premium\": false,\n" +
        "              \"locale\": null\n" +
        "            },\n" +
        "            \"username\": \"FauserneEist\",\n" +
        "            \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "          },\n" +
        "          \"type\": \"NO\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"motion\": \"Employ new CEO or change the employment agreement of current CEO\",\n" +
        "      \"startDate\": 1476525024497,\n" +
        "      \"endDate\": 1477129824497,\n" +
        "      \"group\": [\n" +
        "        {\n" +
        "          \"groupMember\": {\n" +
        "            \"gravatarHash\": \"935898e6728ebf500286179394800122\",\n" +
        "            \"userCapabilities\": {\n" +
        "              \"level2UserEndDate\": null,\n" +
        "              \"premiumEndDate\": null,\n" +
        "              \"level2User\": false,\n" +
        "              \"partner\": false,\n" +
        "              \"premium\": false,\n" +
        "              \"locale\": null\n" +
        "            },\n" +
        "            \"username\": \"Alphabank\",\n" +
        "            \"id\": \"fe97f737-09b2-4a20-a2bd-1bb4c504a687\"\n" +
        "          },\n" +
        "          \"numberOfVoices\": 1000\n" +
        "        },\n" +
        "      ],\n" +
        "      \"castVotesPercentage\": 89.77467093,\n" +
        "      \"totalNumberOfCastVotes\": 96576,\n" +
        "      \"pollInitiator\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"resultExpireDate\": 1477734624497,\n" +
        "      \"id\": \"f1672fda-6689-414c-b68e-94477905e672\",\n" +
        "      \"type\": \"YES_NO\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"polls\": [],\n" +
        "  \"salaryPayments\": [\n" +
        "    {\n" +
        "      \"companyId\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\",\n" +
        "      \"nextPossiblePaymentDate\": 1476967150613,\n" +
        "      \"salaryAmount\": 30000,\n" +
        "      \"date\": 1476880750613,\n" +
        "      \"id\": \"8d86a3d3-29d1-42b8-b9de-e2be5f5706a0\"\n" +
        "    },\n" +
        "  ],\n" +
        "  \"user\": {\n" +
        "    \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "    \"userCapabilities\": {\n" +
        "      \"level2UserEndDate\": null,\n" +
        "      \"premiumEndDate\": null,\n" +
        "      \"level2User\": false,\n" +
        "      \"partner\": true,\n" +
        "      \"premium\": false,\n" +
        "      \"locale\": null\n" +
        "    },\n" +
        "    \"username\": \"FauserneEist\",\n" +
        "    \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "  },\n" +
        "  \"bankAccount\": {\n" +
        "    \"cash\": 156187,\n" +
        "    \"id\": \"443db0b7-c399-4252-8166-061d8de1d634\"\n" +
        "  },\n" +
        "  \"userCapabilities\": {\n" +
        "    \"level2UserEndDate\": null,\n" +
        "    \"premiumEndDate\": null,\n" +
        "    \"level2User\": false,\n" +
        "    \"partner\": true,\n" +
        "    \"premium\": false,\n" +
        "    \"locale\": null\n" +
        "  },\n" +
        "  \"locale\": null\n" +
        "}";

    private UserProfile toTest;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Http.setInstance(httpResponder.getMock());
    }

    @Before
    public void setUp() throws Exception {
        toTest = gson.fromJson(JSON, UserProfile.class);
    }

    @Test
    public void getUserProfile() throws Exception {
        User user = User.getLoggedInUser();
        UserProfile reference = gson.fromJson(httpResponder.getJsonForRequest(
            "/api/userprofiles/FauserneEist"), UserProfile.class);
        UserProfile testObject = UserProfile.getUserProfile(user);
        assertNotNull(user);
        assertEquals(reference, testObject);
    }

    @Test
    public void getCashTransferLogs() throws Exception {
        List<CashTransferLog> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"amount\": 1350,\n" +
        "      \"receiverBankAccount\": \"443db0b7-c399-4252-8166-061d8de1d634\",\n" +
        "      \"senderBankAccount\": \"c4d789b2-5e74-4618-a62e-fedc37fc2e4f\",\n" +
        "      \"date\": 1474445689862,\n" +
        "      \"message\": {\n" +
        "        \"message\": \"Salary from Katholische Kirche AG\",\n" +
        "        \"substitutions\": [],\n" +
        "        \"filledString\": \"Salary from Katholische Kirche AG\"\n" +
        "      },\n" +
        "      \"id\": \"a287ba64-f57b-4a5d-902e-c0180e75d15f\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"amount\": 0,\n" +
        "      \"receiverBankAccount\": \"443db0b7-c399-4252-8166-061d8de1d634\",\n" +
        "      \"senderBankAccount\": \"46c83556-f323-4396-b37a-0991cfe50fdc\",\n" +
        "      \"date\": 1474474440471,\n" +
        "      \"message\": {\n" +
        "        \"message\": \"Salary from BoomBangBoom Inc.\",\n" +
        "        \"substitutions\": [],\n" +
        "        \"filledString\": \"Salary from BoomBangBoom Inc.\"\n" +
        "      },\n" +
        "      \"id\": \"e88cbec9-ae5e-40c0-b231-2c0446a96e3c\"\n" +
        "    },\n" +
        "  ]", new TypeToken<ArrayList<CashTransferLog>>() { }.getType());
        assertEquals(reference, toTest.getCashTransferLogs());
    }

    @Test
    public void getEmployments() throws Exception {
        List<EmploymentAgreement> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"dailyWage\": 5,\n" +
        "      \"startDate\": 1474474477123,\n" +
        "      \"company\": {\n" +
        "        \"securitiesAccountId\": \"b576e190-d033-4b54-9228-f8662f5b7934\",\n" +
        "        \"securityIdentifier\": \"STB46081\",\n" +
        "        \"ceo\": {\n" +
        "          \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "          \"userCapabilities\": {\n" +
        "            \"level2UserEndDate\": null,\n" +
        "            \"premiumEndDate\": null,\n" +
        "            \"level2User\": false,\n" +
        "            \"partner\": true,\n" +
        "            \"premium\": false,\n" +
        "            \"locale\": null\n" +
        "          },\n" +
        "          \"username\": \"FauserneEist\",\n" +
        "          \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "        },\n" +
        "        \"name\": \"BoomBangBoom Inc.\",\n" +
        "        \"id\": \"50ea9f61-af3d-42a3-9a98-43f7757e3adb\"\n" +
        "      },\n" +
        "      \"id\": \"45c61bd6-4b82-4446-9c0f-a81af052edb5\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"dailyWage\": 36,\n" +
        "      \"startDate\": 1475055321695,\n" +
        "      \"company\": {\n" +
        "        \"securitiesAccountId\": \"4aeadc7b-7c76-4a32-bfe6-c51c5037e747\",\n" +
        "        \"securityIdentifier\": \"STK57F4A\",\n" +
        "        \"ceo\": {\n" +
        "          \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "          \"userCapabilities\": {\n" +
        "            \"level2UserEndDate\": null,\n" +
        "            \"premiumEndDate\": null,\n" +
        "            \"level2User\": false,\n" +
        "            \"partner\": true,\n" +
        "            \"premium\": false,\n" +
        "            \"locale\": null\n" +
        "          },\n" +
        "          \"username\": \"FauserneEist\",\n" +
        "          \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "        },\n" +
        "        \"name\": \"kostolowski Inc.\",\n" +
        "        \"id\": \"cc0bdd92-edbc-446f-be14-8dcb61d3f940\"\n" +
        "      },\n" +
        "      \"id\": \"b8f2317f-5f5b-4bb5-9211-b54931ba0042\"\n" +
        "    },\n" +
        "  ]", new TypeToken<ArrayList<EmploymentAgreement>>() { }.getType());
        assertEquals(reference, toTest.getEmployments());
    }

    @Test
    public void getPolls() throws Exception {
        List<Poll> reference = gson.fromJson("[]", new TypeToken<ArrayList<Poll>>() { }.getType());
        assertEquals(reference, toTest.getPolls());
    }

    @Test
    public void getInitiatedPolls() throws Exception {
        List<Poll> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"dailyWage\": 0,\n" +
        "      \"applicant\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"abstentionRule\": \"COUNTS_AS_REFUSAL\",\n" +
        "      \"approvalVotesPercentage\": 0,\n" +
        "      \"company\": {\n" +
        "        \"securityIdentifier\": \"STSB8312\",\n" +
        "        \"listing\": {\n" +
        "          \"startDate\": 1475678348697,\n" +
        "          \"endDate\": null,\n" +
        "          \"securityIdentifier\": \"STSB8312\",\n" +
        "          \"name\": \"randolphduke\",\n" +
        "          \"type\": \"STOCK\"\n" +
        "        },\n" +
        "        \"name\": \"randolphduke\",\n" +
        "        \"id\": \"cb1cbdfe-d23e-42a7-b840-0eec1d00c85f\"\n" +
        "      },\n" +
        "      \"totalNumberOfVoices\": 107576,\n" +
        "      \"votes\": [\n" +
        "        {\n" +
        "          \"voices\": 96576,\n" +
        "          \"voter\": {\n" +
        "            \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "            \"userCapabilities\": {\n" +
        "              \"level2UserEndDate\": null,\n" +
        "              \"premiumEndDate\": null,\n" +
        "              \"level2User\": false,\n" +
        "              \"partner\": true,\n" +
        "              \"premium\": false,\n" +
        "              \"locale\": null\n" +
        "            },\n" +
        "            \"username\": \"FauserneEist\",\n" +
        "            \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "          },\n" +
        "          \"type\": \"NO\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"motion\": \"Employ new CEO or change the employment agreement of current CEO\",\n" +
        "      \"startDate\": 1476525024497,\n" +
        "      \"endDate\": 1477129824497,\n" +
        "      \"group\": [\n" +
        "        {\n" +
        "          \"groupMember\": {\n" +
        "            \"gravatarHash\": \"935898e6728ebf500286179394800122\",\n" +
        "            \"userCapabilities\": {\n" +
        "              \"level2UserEndDate\": null,\n" +
        "              \"premiumEndDate\": null,\n" +
        "              \"level2User\": false,\n" +
        "              \"partner\": false,\n" +
        "              \"premium\": false,\n" +
        "              \"locale\": null\n" +
        "            },\n" +
        "            \"username\": \"Alphabank\",\n" +
        "            \"id\": \"fe97f737-09b2-4a20-a2bd-1bb4c504a687\"\n" +
        "          },\n" +
        "          \"numberOfVoices\": 1000\n" +
        "        },\n" +
        "      ],\n" +
        "      \"castVotesPercentage\": 89.77467093,\n" +
        "      \"totalNumberOfCastVotes\": 96576,\n" +
        "      \"pollInitiator\": {\n" +
        "        \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
        "        \"userCapabilities\": {\n" +
        "          \"level2UserEndDate\": null,\n" +
        "          \"premiumEndDate\": null,\n" +
        "          \"level2User\": false,\n" +
        "          \"partner\": true,\n" +
        "          \"premium\": false,\n" +
        "          \"locale\": null\n" +
        "        },\n" +
        "        \"username\": \"FauserneEist\",\n" +
        "        \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
        "      },\n" +
        "      \"resultExpireDate\": 1477734624497,\n" +
        "      \"id\": \"f1672fda-6689-414c-b68e-94477905e672\",\n" +
        "      \"type\": \"YES_NO\"\n" +
        "    }\n" +
        "  ]", new TypeToken<ArrayList<Poll>>() { }.getType());
        assertEquals(reference, toTest.getInitiatedPolls());
    }

    @Test
    public void getSalaryPayments() throws Exception {
        List<SalaryPayment> reference = gson.fromJson("[\n" +
        "    {\n" +
        "      \"companyId\": \"81dcf5a1-b0b6-462a-a40c-e374619edc2f\",\n" +
        "      \"nextPossiblePaymentDate\": 1476967150613,\n" +
        "      \"salaryAmount\": 30000,\n" +
        "      \"date\": 1476880750613,\n" +
        "      \"id\": \"8d86a3d3-29d1-42b8-b9de-e2be5f5706a0\"\n" +
        "    },\n" +
        "  ]", new TypeToken<ArrayList<SalaryPayment>>() { }.getType());
        assertEquals(reference, toTest.getSalaryPayments());
    }

    @Test
    public void getUser() throws Exception {
        User reference = gson.fromJson("{\n" +
            "  \"gravatarHash\": \"7b7f03e2a716b0efaf4ff8728ad070c3\",\n" +
            "  \"userCapabilities\": {\n" +
            "    \"level2UserEndDate\": null,\n" +
            "    \"premiumEndDate\": null,\n" +
            "    \"level2User\": false,\n" +
            "    \"partner\": true,\n" +
            "    \"premium\": false,\n" +
            "    \"locale\": null\n" +
            "  },\n" +
            "  \"username\": \"FauserneEist\",\n" +
            "  \"id\": \"43986f13-edde-486c-9ef0-718b100a1949\"\n" +
            "}", User.class);
        assertEquals(reference, toTest.getUser());
    }

    @Test
    public void getBankAccount() throws Exception {
        BankAccount reference = gson.fromJson("{\n" +
        "    \"cash\": 156187,\n" +
        "    \"id\": \"443db0b7-c399-4252-8166-061d8de1d634\"\n" +
        "  }", BankAccount.class);
        assertEquals(reference, toTest.getBankAccount());
    }

    @Test
    public void getLocale() throws Exception {
        assertNull(toTest.getLocale());
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(toTest.toString().startsWith(toTest.getClass().getSimpleName()));
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(toTest.equals(toTest));
        assertFalse(toTest.equals(null));
        assertFalse(toTest.equals("Test"));

        UserProfile other = gson.fromJson("{\n" +
            "  \"id\": \"12345\"\n" +
            "}", UserProfile.class);

        assertFalse(toTest.equals(other));
    }

    @Test
    public void testHashCode() throws Exception {
        UserProfile reference = gson.fromJson(JSON, UserProfile.class);
        assertEquals(reference.hashCode(), toTest.hashCode());
    }
}