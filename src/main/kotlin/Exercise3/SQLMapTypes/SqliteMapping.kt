package Exercise3.SQLMapTypes

class SqliteMapping : MapType() {
    override val Int: String
        get() = "INTEGER"
    override val String: String
        get() = "TEXT"
    override val Boolean: String
        get() = "BOOL"
    override val Double: String
        get() = "REAL"
}