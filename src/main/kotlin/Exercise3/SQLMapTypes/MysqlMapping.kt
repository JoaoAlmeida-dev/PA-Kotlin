package Exercise3.SQLMapTypes

class MysqlMapping : MapType() {
    override val Int: String
        get() = "INT"
    override val String: String
        get() = "VARCHAR(255)"
    override val Boolean: String
        get() = "BOOLEAN"
    override val Double: String
        get() = "DOUBLE"
}