package org.chromium.services.service_manager;

import org.chromium.mojo.bindings.Interface;

public interface InterfaceFactory {
    Interface createImpl();
}

