# CES Dataset to TCSV Converter explanation

The experimentation of real world dataset will be using CES (Customer Expenditure Survey) Dataset. The dataset is using CSV format while this aplication using TCSV format. So, this class is exist to convert the dataset to our TCSV format. <br/>

The dataset sturcture has only 2 columns. The first column is ID and the second column is an item. In the same ID, the first item is city of residence, the second item is monthly income of the family, the third item is number of the members of the family, and the rest of the item is the product that the family bought. <br/>

From that structure, we can conlude that the first three item of the same ID should not be in the TCSV. So, we only need the ID and the products.