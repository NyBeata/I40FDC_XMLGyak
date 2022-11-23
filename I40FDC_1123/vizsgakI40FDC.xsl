<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">

        <html>
            <body>
                <h2>I40FDC kurzusok</h2>

                <table border = "4">
                    <tr bgcolor = "#9acd32">
                        <th>Kurzus</th>
                        <th>Helyszin</th>
                        <th>Nap</th>
                        <th>Tol</th>
                        <th>Ig</th>
                        <th>Oktato</th>
                        <th>Jegy</th>
                    </tr>

                    <xsl:for-each select = "vizsgak/vizsga">
                        <tr>
                            <td><xsl:value-of select="kurzus"></xsl:value-of></td>
                            <td><xsl:value-of select="helyszin"></xsl:value-of></td>
                            <td><xsl:value-of select="idopont/nap"></xsl:value-of></td>
                            <td><xsl:value-of select="idopont/tol"></xsl:value-of></td>
                            <td><xsl:value-of select="idopont/ig"></xsl:value-of></td>
                            <td><xsl:value-of select="oktato"></xsl:value-of></td>
                            <td><xsl:value-of select="jegy"></xsl:value-of></td>
                        </tr>
                    </xsl:for-each>>

                </table>
                
                <h2>Átlag</h2>
                <table border = "4">
                    <tr bgcolor = "#2CE5D4">
                        <th>Neptunkód</th>
                        <th>Átlag</th>
                    </tr>

                    <xsl:for-each select="vizsgak">
                        <tr>
                            <td>
                                <xsl:value-of select="@_id"/>
                            </td>
                            <td><xsl:value-of select="atlag"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>