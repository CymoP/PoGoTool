<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:apply-templates select="*"/>

        <xsl:result-document href="{*/name()}.sql" method="text">
            <xsl:copy-of select="node()"/>
        </xsl:result-document>
    </xsl:template>

    <xsl:template match="data">
        <xsl:for-each select="./*">
            <xsl:result-document href="{name()}.sql" method="text">
                <xsl:copy-of select="node()"/>
            </xsl:result-document>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>