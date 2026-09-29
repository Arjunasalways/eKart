import os
import re
import requests

# ============================================================
# CONFIGURATION
# ============================================================

BASE_URL = "http://localhost:8080"
IMAGE_FOLDER = "images"

# Your JWT token
TOKEN = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYWxsaWthcmp1bkBleGFtcGxlLmNvbSIsImlhdCI6MTc4Njc3MjQ5OCwiZXhwIjoxNzg2ODU4ODk4fQ._VkfSroxciASbwNJFFC52NEp3RsbU7ZV2eXe-LMLDXx-ZY2E05E4osktNhXSeXKw"

# Upload only variants 25 to 48
START_VARIANT = 49
END_VARIANT = 71


# ============================================================
# UPLOAD FUNCTION
# ============================================================

def upload_image(variant_id, image_path, primary_image):

    url = f"{BASE_URL}/admin/product-variants/{variant_id}/images"

    headers = {
        "Authorization": f"Bearer {TOKEN}"
    }

    data = {
        "primaryImage": str(primary_image).lower()
    }

    with open(image_path, "rb") as image_file:

        files = {
            "file": (
                os.path.basename(image_path),
                image_file,
                "image/jpeg"
            )
        }

        response = requests.post(
            url,
            headers=headers,
            data=data,
            files=files
        )

    return response


# ============================================================
# MAIN
# ============================================================

def main():

    if not os.path.exists(IMAGE_FOLDER):
        print("❌ images folder not found!")
        return

    print("========================================")
    print("       eKart Bulk Image Uploader")
    print("========================================")
    print()

    uploaded = 0
    failed = 0

    # Get all files from images folder
    files = os.listdir(IMAGE_FOLDER)

    # Sort alphabetically
    files.sort()

    for filename in files:

        image_path = os.path.join(IMAGE_FOLDER, filename)

        # Ignore directories
        if not os.path.isfile(image_path):
            continue

        # Only process image files
        if not filename.lower().endswith(
            (".jpg", ".jpeg", ".png", ".webp")
        ):
            continue

        # ----------------------------------------------------
        # Extract variant ID from beginning of filename
        #
        # Example:
        # 25-iphone-16-pro-black-1.jpg
        #
        # variant_id = 25
        # ----------------------------------------------------

        match = re.match(r"^(\d+)-", filename)

        if not match:
            print(f"⚠️ Skipping: {filename}")
            print("   Could not determine variant ID.")
            continue

        variant_id = int(match.group(1))

        # Only upload variants 25 to 48
        if variant_id < START_VARIANT or variant_id > END_VARIANT:
            continue

        # ----------------------------------------------------
        # Determine image number
        #
        # Example:
        # ...-1.jpg → primary
        # ...-2.jpg → not primary
        # ----------------------------------------------------

        image_match = re.search(r"-(\d+)\.(jpg|jpeg|png|webp)$",
                                filename,
                                re.IGNORECASE)

        if image_match:

            image_number = int(image_match.group(1))

            primary_image = image_number == 1

        else:

            print(f"⚠️ Skipping: {filename}")
            print("   Could not determine image number.")
            continue

        print("----------------------------------------")
        print(f"Variant ID   : {variant_id}")
        print(f"Image        : {filename}")
        print(f"Primary      : {primary_image}")

        try:

            response = upload_image(
                variant_id,
                image_path,
                primary_image
            )

            if response.status_code >= 200 and response.status_code < 300:

                print("✅ Upload successful")
                uploaded += 1

            else:

                print("❌ Upload failed")
                print(f"Status code : {response.status_code}")
                print(f"Response    : {response.text}")

                failed += 1

        except Exception as e:

            print("❌ Error uploading image")
            print(e)

            failed += 1

    print()
    print("========================================")
    print("              UPLOAD SUMMARY")
    print("========================================")
    print(f"Successful uploads : {uploaded}")
    print(f"Failed uploads     : {failed}")
    print("========================================")


if __name__ == "__main__":
    main()