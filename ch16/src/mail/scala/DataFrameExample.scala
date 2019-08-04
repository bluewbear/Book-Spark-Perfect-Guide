object DataFrameExample extends Serializable {
    def main(args: Array[String]): Unit = {
        val pathToDataFolder = args(0)

        val spark = SparkSession.builder()
        .appName(“Spark Example”)
        .config(“spark.sql.warehouse.dir”,”/user/hive/warehouse”)
        .getOrCreate()

        spark.udf.register(“myUDF”, someUDF(_:String): String)

        val df = spark.read.json(pathToDataFolder + “data.json”)
        val manipulated = df.groupBy(expr(“myUDF(group)“)).sum().collect().foreach(x => println(x))
    }
}