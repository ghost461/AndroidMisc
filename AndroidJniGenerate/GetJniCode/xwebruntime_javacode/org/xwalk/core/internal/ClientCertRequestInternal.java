package org.xwalk.core.internal;

import java.security.Principal;
import java.security.PrivateKey;
import java.util.List;

@XWalkAPI(instance=ClientCertRequestHandlerInternal.class) public interface ClientCertRequestInternal {
    @XWalkAPI void cancel();

    @XWalkAPI String getHost();

    @XWalkAPI String[] getKeyTypes();

    @XWalkAPI int getPort();

    @XWalkAPI Principal[] getPrincipals();

    @XWalkAPI void ignore();

    @XWalkAPI void proceed(PrivateKey arg1, List arg2);
}

