package org.chromium.network.mojom;

import org.chromium.mojo.bindings.Callbacks$Callback0;
import org.chromium.mojo.bindings.DeserializationException;
import org.chromium.mojo.bindings.Interface$Manager;
import org.chromium.mojo.bindings.Interface;

public interface NetworkServiceTest extends Interface {
    public interface AddRulesResponse extends Callback0 {
    }

    public interface MockCertVerifierAddResultForCertAndHostResponse extends Callback0 {
    }

    public interface MockCertVerifierSetDefaultResultResponse extends Callback0 {
    }

    public interface Proxy extends org.chromium.mojo.bindings.Interface$Proxy, NetworkServiceTest {
    }

    public interface SetShouldRequireCtResponse extends Callback0 {
    }

    public final class ShouldRequireCt {
        public static final int DONT_REQUIRE = 2;
        private static final boolean IS_EXTENSIBLE = false;
        public static final int REQUIRE = 1;
        public static final int RESET;

        private ShouldRequireCt() {
            super();
        }

        public static boolean isKnownValue(int arg0) {
            switch(arg0) {
                case 0: 
                case 1: 
                case 2: {
                    return 1;
                }
            }

            return 0;
        }

        public static void validate(int arg1) {
            if(ShouldRequireCt.isKnownValue(arg1)) {
                return;
            }

            throw new DeserializationException("Invalid enum value.");
        }
    }

    public interface SimulateNetworkChangeResponse extends Callback0 {
    }

    public static final Manager MANAGER;

    static {
        NetworkServiceTest.MANAGER = NetworkServiceTest_Internal.MANAGER;
    }

    void addRules(Rule[] arg1, AddRulesResponse arg2);

    void mockCertVerifierAddResultForCertAndHost(X509Certificate arg1, String arg2, CertVerifyResult arg3, int arg4, MockCertVerifierAddResultForCertAndHostResponse arg5);

    void mockCertVerifierSetDefaultResult(int arg1, MockCertVerifierSetDefaultResultResponse arg2);

    void setShouldRequireCt(int arg1, SetShouldRequireCtResponse arg2);

    void simulateCrash();

    void simulateNetworkChange(int arg1, SimulateNetworkChangeResponse arg2);
}

