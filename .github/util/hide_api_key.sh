# This script replaces the key $1 with the string "VISIBLE_API_KEY" in all the subfiles of the directory $2
# Usage: ./hide_api_key.sh <API_KEY> <DIRECTORY>
# Example:
# ./hide_api_key.sh "1234567890" "src"


# Check if the number of arguments is correct
if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./hide_api_key.sh <API_KEY> <DIRECTORY>"
    exit 1
fi

# Check if the directory exists
if [ ! -d "$2" ]; then
    echo "Directory $2 does not exist"
    exit 1
fi

# Replace the key $1 with the string "VISIBLE_API_KEY" in all the subfiles of the directory $2

# Find all the files in the directory $2 recursively
find $2 -type f | while read file; do
    # Replace the key $1 with the string "VISIBLE_API_KEY" in the file
    sed -i "s/$1/PRIVATE_API_KEY/g" $file
done

echo "API key hidden successfully"

exit 0