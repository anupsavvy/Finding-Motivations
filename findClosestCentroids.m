function idx = findClosestCentroids(X,centroids)
	[m,n] = size(X);
	idx = zeros(m,1);
	dist = zeros(size(centroids,1),1);
	format long;

	% for all samples
	for i = 1:m
		% for all centroids
		for j = 1:size(dist,1)
			%dist(j) = sqrt(sum((X(i,:) - centroids(j,:)).^2));
		    dist(j) = bsxfun(@rdivide, bsxfun(@rdivide, sum(X(i,:).*centroids(j,:)), sqrt(sum(X(i,:).^2))), sqrt(sum(centroids(j,:).^2)));
		end
		[val,pos] = max(dist);
		idx(i) = pos; 
	end
end