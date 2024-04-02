/*
 * Copyright (c) 2018-2024 "GraphGrid,"
 * GraphGrid, Inc. [https://graphgrid.com]
 *
 * This file is part of GraphGrid. The included source
 * code can be redistributed and/or modified under the terms of the
 * GNU AFFERO GENERAL PUBLIC LICENSE Version 3
 * (http://www.fsf.org/licensing/licenses/agpl-3.0.html) as found
 * in the associated LICENSE.txt file.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 */
package com.graphgrid.sdk.ml.constant;

public final class PipelineStageType
{

    public static final String ESTIMATOR = "Estimator";
    public static final String TRANSFORMER = "Transformer";

    // Feature
    public static final String INDEX_TO_STRING = "IndexToString";
    public static final String STRING_INDEXER = "StringIndexer";
    public static final String VECTOR_ASSEMBLER = "VectorAssembler";
    public static final String WORD2VEC = "Word2Vec";
    public static final String TOKENIZER = "Tokenizer";
    public static final String STOP_WORDS_REMOVER = "StopWordsRemover";
    public static final String N_GRAM = "NGram";
    public static final String FEATURE_HASHER = "FeatureHasher";
    public static final String ONE_HOT_ENCODER_ESTIMATOR = "OneHotEncoderEstimator";

    // Classification
    public static final String LOGISTIC_REGRESSION = "LogisticRegression";
    public static final String DECISION_TREE_CLASSIFIER = "DecisionTreeClassifier";
    public static final String RANDOM_FOREST_CLASSIFIER = "RandomForestClassifier";

    // Regression
    public static final String LINEAR_REGRESSION = "LinearRegression";

    // Clustering
    public static final String KMEANS = "KMeans";

    // Recommendation
    public static final String ALS = "ALS";
}
