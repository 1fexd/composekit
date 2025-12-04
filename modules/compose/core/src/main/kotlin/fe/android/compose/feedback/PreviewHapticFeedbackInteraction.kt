package fe.android.compose.feedback

public object PreviewHapticFeedbackInteraction : HapticFeedbackInteraction {
    override fun copy(content: String, type: FeedbackType) {}
    override fun openUri(uri: String, type: FeedbackType) {}
    override fun perform(type: FeedbackType) {}
}
