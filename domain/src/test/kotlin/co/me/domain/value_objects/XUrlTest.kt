package co.me.domain.value_objects

import junit.framework.Assert.assertEquals
import org.junit.Test

class XUrlTest {

    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating invalid XUrl`() {
        XUrl("notAnUrl")
    }

    @Test
    fun `should allow creating a valid XUrl without protocol nor www`() {
        XUrl("notAnUrl.com")
    }

    @Test
    fun `should allow creating a valid XUrl with protocol and without www`() {
        XUrl("https://notAnUrl.com")
    }

    @Test
    fun `should allow creating a valid XUrl with protocol and www`() {
        XUrl("https://www.notAnUrl.com")
    }

    @Test
    fun `should allow creating a valid XUrl with protocol and a custom subdomain`() {
        XUrl("https://api.notAnUrl.com")
    }

    @Test
    fun `should set value to received url`() {
        val xUrl = XUrl("https://api.notAnUrl.com")

        assertEquals(xUrl.value, "https://api.notAnUrl.com")
    }

}