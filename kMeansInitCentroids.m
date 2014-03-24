function centroids = kMeansInitCentroids(X,K)
	rand_index = randperm(size(X,1));
	centroids = X(rand_index(:,1:K),:);
end