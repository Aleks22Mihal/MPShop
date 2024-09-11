package mpshop.app.presentation.utils


fun String.ensureValidUrl(): String {
    return if (!this.startsWith("http://") && !this.startsWith("https://")) {
        val res = this.replace("[\"", "").replace("\"]", "")
        println(res)
        res
    } else {
        println(this)
        this
    }
}