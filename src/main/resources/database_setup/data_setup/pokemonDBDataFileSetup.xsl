<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="data">
        <xsl:result-document href="pokemon.sql">
            <xsl:copy-of select="pokemon/node()"/>
        </xsl:result-document>

        <xsl:result-document href="fastmove.sql">
            <xsl:copy-of select="fastmove/node()"/>
        </xsl:result-document>

        <xsl:result-document href="chargemove.sql">
            <xsl:copy-of select="chargemove/node()"/>
        </xsl:result-document>

        <xsl:result-document href="pokemonfastmove.sql">
            <xsl:copy-of select="pokemonfastmove/node()"/>
        </xsl:result-document>

        <xsl:result-document href="pokemonchargemove.sql">
            <xsl:copy-of select="pokemonchargemove/node()"/>
        </xsl:result-document>

        <xsl:result-document href="user.sql">
            <xsl:copy-of select="user/node()"/>
        </xsl:result-document>

        <xsl:result-document href="role.sql">
            <xsl:copy-of select="role/node()"/>
        </xsl:result-document>

        <xsl:result-document href="userrole.sql">
            <xsl:copy-of select="userrole/node()"/>
        </xsl:result-document>

        <xsl:result-document href="allData.sql">
            <xsl:copy-of select="pokemon/node()"/>
            <xsl:copy-of select="fastmove/node()"/>
            <xsl:copy-of select="chargemove/node()"/>
            <xsl:copy-of select="pokemonfastmove/node()"/>
            <xsl:copy-of select="pokemonchargemove/node()"/>
            <xsl:copy-of select="role/node()"/>
            <xsl:copy-of select="user/node()"/>
            <xsl:copy-of select="userrole/node()"/>
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>