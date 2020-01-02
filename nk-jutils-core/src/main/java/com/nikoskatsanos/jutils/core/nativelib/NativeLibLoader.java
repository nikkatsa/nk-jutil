package com.nikoskatsanos.jutils.core.nativelib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class NativeLibLoader {

    private NativeLibLoader() {
    }

    public static void loadFromJar(final String nativeLibResource) {

        final File tmp = Paths.get(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString()).toFile();
        tmp.deleteOnExit();
        try (final InputStream is = NativeLibLoader.class.getResourceAsStream(nativeLibResource)) {
            Files.copy(is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            throw new RuntimeException(String.format("Failed to load native lib resource %s", nativeLibResource), e);
        }

        final String[] parts = nativeLibResource.split(File.separator);
        final File extractedNativeLib = new File(tmp, parts[parts.length - 1]);
        try {
            System.load(tmp.getAbsolutePath());
        } catch (final Throwable t) {
            throw new RuntimeException(
                String.format("Failed to load native lib '%s' from temporary extraction location '%s'", nativeLibResource, extractedNativeLib.getAbsolutePath()), t);
        }
    }
}
