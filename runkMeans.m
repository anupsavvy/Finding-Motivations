function [centroids, idx, Jcost, clusterSize] = runkMeans(X,initial_centroids,max_iters)
	% Initialize values
	[m n] = size(X);
	K = size(initial_centroids,1);
	clusterSize = zeros(K,1);
	centroids = initial_centroids;
	previous_centroids = centroids;
	idx = zeros(m,1);
	Jcost = zeros(max_iters,1);

	%figure;
	%hold on;

	for i = 1:max_iters

		% For each example in X, assign it to the closest centroid

		idx = findClosestCentroids(X,centroids);

		% plot progress of centroids

		% compute new centroids

		[centroids,clusterSize] = computeCentroids(X,idx,K);

		Jcost(i,1) = costFunction(X,centroids,idx);

	end

	%hold off;
end