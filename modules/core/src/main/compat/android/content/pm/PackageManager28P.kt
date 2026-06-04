package android.content.pm

import fe.composekit.core.AndroidVersion

public fun PackageManager.getSignature(packageName: String): Signature? {
    @Suppress("DEPRECATION") val flag = if (AndroidVersion.isAtLeastApi28P()) PackageManager.GET_SIGNING_CERTIFICATES
    else PackageManager.GET_SIGNATURES

    val info = getPackageInfo(packageName, flag)

    @Suppress("DEPRECATION")
    return if (AndroidVersion.isAtLeastApi28P()) info.signingInfo?.signingCertificateHistory?.firstOrNull()
    else info.signatures?.firstOrNull()
}

// TODO: Use hasSigningCertificate on P+
//pm.hasSigningCertificate(packageName, certificate, PackageManager.CERT_INPUT_SHA256)
