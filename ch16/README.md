./bin/spark-submit \
--master local \
-—deploy-mode cluster \
-—class DataFrameExample
DataFrameExample.jar \
test_data_folder