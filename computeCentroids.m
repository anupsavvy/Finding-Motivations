function [centroids,clusterSize] = computeCentroids(X,idx,K)

	[m,n] = size(X);
	centroids = zeros(K,n);
	clusterSize = zeros(K,1);
	for i = 1:K
		count = 0;
	    vectorSum = zeros(1,n);
		for j = 1:m
			if idx(j,1) == i
				count++;
				vectorSum = vectorSum + X(j,:);
			end
		end
		if count > 0
			centroids(i,:) = vectorSum/count;
		end
        clusterSize(i) = count;
	end
end